package com.avectis.transportcontrol.control.scanner;

import jssc.SerialPort;
import jssc.SerialPortEventListener;
/**
 *
 * @author Ivan
 */
public class CardScannerAdapterTest implements CardScannerAdapter {
    
    public SerialPort serialPort; //COM порт сканера карт
    
    private int baudRate;
    private int bitCount;
    private int stopBits;
    private int parity;
    private String portName;
    
    public CardScannerAdapterTest(){
    }
    private void init(){
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }
        
    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public void setBitCount(int bitCount) {
        this.bitCount = bitCount;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    @Override
    public void connect(){
    }
    @Override
    public void disconnect(){
    
    }
    @Override
    public void reconnect(){
        disconnect();
        connect();
    }
    @Override
    public boolean isConnected(){
        return false;
    }
    @Override
    public void addPortListener(SerialPortEventListener listener){       
        
    }
    @Override
    public void removePortListener(){       
        
    }
    @Override
    public String getReceivedData() {
        return null;
    }
    @Override
    public void ClearBuffers(){
        
    }
    public void destroy(){
        
    }
    
}


