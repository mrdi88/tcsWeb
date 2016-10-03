package com.avectis.transportcontrol.control.barrier;

import java.net.InetAddress;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.net.TCPMasterConnection;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ivan on 30.08.2016.
 */
public class BarrierAdapterTCP implements BarrierAdapter{
    public enum RequestType {
    WRITE_COIL,
    READ_INPUT_DISCRETES,
    }
        
    private TCPMasterConnection connection;
    private ModbusTCPTransaction transaction;
    private ModbusResponse response;
    private ReadInputDiscretesResponse rInDiscrResp;
    
    private String ipAddr;
    private int port = Modbus.DEFAULT_PORT;
    private int unitId = 1;
    private int pulseLength = 1000;

    public BarrierAdapterTCP(String IP, int port, int unitId, int pulseLength){
        try{
            this.ipAddr = IP;
            this.port = port;
            this.unitId = unitId;
            this.pulseLength = pulseLength;
            
            connection = new TCPMasterConnection(InetAddress.getByName(this.ipAddr));
            connection.setTimeout(3000);
            connection.setPort(this.port);
            connection.connect();
            
            transaction = new ModbusTCPTransaction(connection);
        }
        catch (Exception e){

        }
    }
   
    @Override //Получения состояния щлагбаума
    public void open() {
        doR_W(RequestType.WRITE_COIL, 1, 0);
        doR_W(RequestType.WRITE_COIL, 0, 1);
        TimerTask TT = new TimerTask(){
            @Override
            public void run() {  
                doR_W(RequestType.WRITE_COIL, 0, 0);
            }
        };
        Timer T = new Timer();
        T.schedule(TT, pulseLength);
    }

    @Override//Открытие шлагбаума
    public void close() {
        doR_W(RequestType.WRITE_COIL, 0, 0);
        doR_W(RequestType.WRITE_COIL, 1, 1);
        TimerTask TT = new TimerTask(){
            @Override
            public void run() {  
                doR_W(RequestType.WRITE_COIL, 1, 0);
            }
        };
        Timer T = new Timer();
        T.schedule(TT, pulseLength);
    }

    @Override//Закрытие шлагбаума
    public boolean getState(int inputNumber) {
        return (doR_W(RequestType.READ_INPUT_DISCRETES, 0, 2))[inputNumber];
    }
    
    private boolean[] doR_W(RequestType requestType, int pos, int x) {
    try {
        if (connection.isConnected()) {
            ModbusRequest request = null;
            switch (requestType) {
                case WRITE_COIL:
                    request = new WriteCoilRequest(pos, (x != 0));
                    break;
                case READ_INPUT_DISCRETES: {
                    request = new ReadInputDiscretesRequest(pos, x);
                    break;
                }
            }

            request.setUnitID(unitId);
            this.transaction.setRequest(request);
            this.transaction.execute();
            this.response = transaction.getResponse();

            if (requestType == RequestType.READ_INPUT_DISCRETES) {
                rInDiscrResp = (ReadInputDiscretesResponse) transaction.getResponse();
                boolean[] result = new boolean[x];
                for(int i = 0; i < result.length; i++){
                    result[i] = rInDiscrResp.getDiscretes().getBit(i);
                }
                
                return result;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new boolean[1] ;
}
    
    public void onDestroy(){
        connection.close();        
    }    
}
