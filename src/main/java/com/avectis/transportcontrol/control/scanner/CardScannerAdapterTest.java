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


    public void destroy(){
        
    }

    @Override
    public void addDataListener(DataListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeDataListener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


