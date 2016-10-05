package com.avectis.transportcontrol.control.barrier;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.net.TCPMasterConnection;

/**
 * Created by Ivan on 30.08.2016.
 */
public class BarrierAdapterTest implements BarrierAdapter{
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
    
    public BarrierAdapterTest(){}
    public void init() {
        return;
    }
   
    @Override //Получения состояния щлагбаума
    public void open() {
        return;
    }

    @Override//Открытие шлагбаума
    public void close() {
        return;
    }

    @Override//Закрытие шлагбаума
    public boolean getState(int inputNumber) {
        return true;
    }
    
    private boolean[] doR_W(RequestType requestType, int pos, int x) {
        return new boolean[1] ;
    }
    
    public void onDestroy(){
        return;
    }    

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public void setPulseLength(int pulseLength) {
        this.pulseLength = pulseLength;
    }
    
}
