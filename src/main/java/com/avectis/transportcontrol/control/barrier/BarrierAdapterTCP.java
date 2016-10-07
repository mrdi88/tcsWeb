package com.avectis.transportcontrol.control.barrier;

import com.avectis.transportcontrol.exception.ConnectionFailException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/**
 * Created by Ivan on 30.08.2016.
 */
public class BarrierAdapterTCP implements BarrierAdapter{
    private String ipAddr;
    private int port = 502;
    private byte unitId = 1;
    private int pulseLength = 1000;
    
    private Socket socket;
    
    public BarrierAdapterTCP(){}
    public BarrierAdapterTCP(String IP, int port, byte unitId, int pulseLength){        
            this.ipAddr = IP;
            this.port = port;
            this.unitId = unitId;
            this.pulseLength = pulseLength;                    
    }
   
    @Override //Открытие шлагбаума
    public void open() throws ConnectionFailException{
        try {        
            modbusRequest((byte)0x05,writeCoilReq((short)1,false));
            modbusRequest((byte)0x05,writeCoilReq((short)0,true));
        } catch (ConnectionFailException ex) {
            throw ex;
        }
        
        
        TimerTask TT = new TimerTask(){
            @Override
            public void run() 
            {  
                try {
                    modbusRequest((byte)0x05,writeCoilReq((short)0,false));
                } catch (ConnectionFailException ex) {
                    ex.printStackTrace();
                }
            }
        };
        Timer T = new Timer();
        T.schedule(TT, pulseLength);
    }
    @Override //Закрытие шлагбаума
    public void close() throws ConnectionFailException {
        try {        
            modbusRequest((byte)0x05,writeCoilReq((short)0,false));
            modbusRequest((byte)0x05,writeCoilReq((short)1,true));
        } catch (ConnectionFailException ex) {
            throw ex;
        }
                
        TimerTask TT = new TimerTask(){
            @Override
            public void run() 
            {   
                try{
                    modbusRequest((byte)0x05,writeCoilReq((short)1,false));
                } catch (ConnectionFailException ex) {
                    ex.printStackTrace();
                }
            }
        };
        Timer T = new Timer();
        T.schedule(TT, pulseLength);
    }
    @Override //Получения состояния щлагбаума
    public boolean getState(int inputNumber) throws ConnectionFailException {
        byte[] responce;
        try {
            responce = modbusRequest((byte)0x02,readInputReq((short)0,(short)8));
            
            byte inputs;
            if(responce != null && responce[7] == 0x02){
                inputs = responce[9];
            }else{
                inputs = 0;
            }
            if(inputNumber == 0){
                return ((inputs & 0x01) > 0)?(true):(false);
            }
            if(inputNumber == 1){
                return ((inputs & 0x02) > 0)?(true):(false);
            }
        } catch (ConnectionFailException ex) {
            throw ex;
        }
        
        return false;
    }
    
    public byte[] modbusRequest(byte functionCode, byte[] data) throws ConnectionFailException{
        try {
            byte[] request = new byte[8 + data.length];
            Random random = new Random();
            short tempID = (short)(random.nextInt() & 0x7FFF);
            short tempLength = (short)(data.length + 2);
            
            request[0] = (byte)((tempID >> 8) & 0x00FF);
            request[1] = (byte)((tempID) & 0x00FF);                      
            request[2] = 0x00;
            request[3] = 0x00;
            request[4] = (byte)((tempLength >> 8) & 0x00FF);
            request[5] = (byte)((tempLength) & 0x00FF);
            request[6] = unitId;
            request[7] = functionCode;
            
            for(int i=0,j=8; i < data.length;i++,j++){
                request[j] = data[i];
            }
            
            InetAddress ipAddress = InetAddress.getByName(this.ipAddr);
            this.socket = new Socket(ipAddress,this.port);
            this.socket.setSoTimeout(500);
            
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            
            sout.write(request);
            sout.flush();
            
            byte[] responce = new byte[request.length];
            sin.read(responce);
            this.socket.close();
            return responce;
            
        } 
        catch (UnknownHostException ex){
           throw new ConnectionFailException("Unable to find remote host ("+this.ipAddr+":"+this.port+").\n" + ex.getMessage());
        }
        catch (IOException ex) {
           throw new ConnectionFailException("Unable to connect remote host ("+this.ipAddr+":"+this.port+").\n" + ex.getMessage());
        }    
     }
    private byte[] writeCoilReq(short coilNumber, boolean value){
        byte[] result = new byte[4];
        result[0] = (byte)((coilNumber >> 8) & 0x00FF);
        result[1] = (byte)((coilNumber) & 0x00FF);
        result[2] = (value)?(byte)(0xFF):(byte)(0x00);
        result[3] = 0x00;
        
        return result;
    }
    private byte[] readInputReq(short startAddr, short length){
        byte[] result = new byte[4];
        result[0] = (byte)((startAddr >> 8) & 0x00FF);
        result[1] = (byte)((startAddr) & 0x00FF);
        result[2] = (byte)((length >> 8) & 0x00FF);
        result[3] = (byte)((length) & 0x00FF);
        
        return result;
    }   
    public void onDestroy(){
        try { 
            if(this.socket != null && this.socket.isConnected())
            this.socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    

    public String getIpAddr() {
        return ipAddr;
    }
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public byte getUnitId() {
        return unitId;
    }
    public void setUnitId(byte unitId) {
        this.unitId = unitId;
    }

    public int getPulseLength() {
        return pulseLength;
    }
    public void setPulseLength(int pulseLength) {
        this.pulseLength = pulseLength;
    }    
}
