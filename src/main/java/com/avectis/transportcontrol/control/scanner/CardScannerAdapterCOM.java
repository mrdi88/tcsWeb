package com.avectis.transportcontrol.control.scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
/**
 *
 * @author Ivan
 */
public class CardScannerAdapterCOM implements CardScannerAdapter, SerialPortEventListener{
    private String portName;
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;
    
    private SerialPort serialPort; //COM порт сканера карт
    private DataListener externalDataListener;
        
    public CardScannerAdapterCOM(){
    }
    public CardScannerAdapterCOM(String portName){
        this.portName = portName;
        this.baudRate = 9600; 
        this.dataBits = 8;
        this.stopBits = 1;
        this.parity = 0;
        init();
    }
    private void init(){    
        this.serialPort = new SerialPort(this.portName);         
        try{
            if (!serialPort.isOpened()){
                serialPort.openPort();
                this.serialPort.setParams(this.baudRate, this.dataBits, this.stopBits, this.parity);
                this.serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);                
                this.serialPort.addEventListener(this, this.serialPort.MASK_RXCHAR);
            }
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }    
    }
          
    public void onDestroy(){
        try {
            this.serialPort.closePort();
        } catch (SerialPortException ex) {
            Logger.getLogger(CardScannerAdapterCOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override 
    public void addDataListener(DataListener listener){
        this.externalDataListener = listener;
    }
    @Override 
    public void removeDataListener(){
        externalDataListener = null;
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
        return this.serialPort.isOpened();
    }
    
    @Override 
    public void serialEvent(SerialPortEvent eventData) {
        try{
            Thread.sleep(10);
            String tempStr = this.serialPort.readString();
            this.externalDataListener.onDataReceive(tempStr);
            this.serialPort.purgePort(serialPort.PURGE_RXCLEAR + serialPort.PURGE_TXCLEAR);
        }
        catch (Exception ex){
            ex.printStackTrace();    
        }
        
    }
}

