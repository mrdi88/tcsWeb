package com.avectis.transportcontrol.control.relay;

import com.avectis.transportcontrol.exception.ConnectionFailException;
import java.net.Socket;

/**
 * Created by Ivan on 30.08.2016.
 */
public class RelayAdapterTest implements RelayAdapter{
    private String ipAddr;
    private int port = 502;
    private byte unitId = 1;
    private int pulseLength = 1000;
    
    private Socket socket;
    
    public RelayAdapterTest(){}
    public RelayAdapterTest(String IP, int port, byte unitId, int pulseLength){        
            this.ipAddr = IP;
            this.port = port;
            this.unitId = unitId;
            this.pulseLength = pulseLength;                    
    }
    @Override //Открытие шлагбаума
    public void pulseDO1() throws ConnectionFailException{
        return;
    }
    @Override //Открытие шлагбаума
    public void pulseDO2() throws ConnectionFailException{
        return;
    }
    @Override //Открытие шлагбаума
    public void turnOnDO1() throws ConnectionFailException{
        return;
    }
    @Override //Открытие шлагбаума
    public void turnOnDO2() throws ConnectionFailException{
        return;
    }
    @Override //Открытие шлагбаума
    public void turnOffDO1() throws ConnectionFailException{
        return;
    }
    @Override //Открытие шлагбаума
    public void turnOffDO2() throws ConnectionFailException{
        return;
    }
    @Override //Получения состояния щлагбаума
    public boolean getState(int inputNumber) throws ConnectionFailException{
        return false;
    }
    public void onDestroy(){
        return;
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
