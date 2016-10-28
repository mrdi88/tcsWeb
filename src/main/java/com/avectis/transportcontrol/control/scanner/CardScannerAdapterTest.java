package com.avectis.transportcontrol.control.scanner;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
/**
 *
 * @author Ivan
 */
public class CardScannerAdapterTest implements CardScannerAdapter, SerialPortEventListener{
    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;
    private String ipAddr;
    private int port;
    
    private SerialPort serialPort; //COM порт сканера карт
    private DataListener externalDataListener;

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public void setPort(int port) {
        this.port = port;
    }
   
    public CardScannerAdapterTest(){
    }
    public CardScannerAdapterTest(String portName){
    }
    private void init(){    
        return;
    }
          
    public void onDestroy(){
        return;
    }
    
    @Override 
    public void addDataListener(DataListener listener){
        return;
    }
    @Override 
    public void removeDataListener(){
    } 

    public void setPortName(String portName) {
        this.portName = portName;
    }
    
    public String getPortName() {
        return this.portName;
    }       
    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }
    public void setDataBits(int bitCount) {
        this.dataBits = bitCount;
    }
    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }
    public void setParity(int parity) {
        this.parity = parity;
    }
    public boolean isConnected(){
        return false;
    }
    
    @Override 
    public void serialEvent(SerialPortEvent eventData) {
    }
}

