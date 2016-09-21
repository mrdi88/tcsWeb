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
public class CardScannerAdapterCOM implements CardScannerAdapter {
    
    public SerialPort serialPort; //COM порт сканера карт
    
    private int baudRate;
    private int bitCount;
    private int stopBits;
    private int parity;
    private String portName;
    
    public CardScannerAdapterCOM(){
    }
    private void init(){
        //System.out.println("CardScannerAdapterCOM INIT");
        this.serialPort = new SerialPort(portName);
        this.baudRate = 9600; 
        this.bitCount = 8;
        this.stopBits = 1;
        this.parity = 0;
         
        try{
            if (!serialPort.isOpened()){
                serialPort.openPort();
                this.serialPort.setParams(this.baudRate, this.bitCount, this.stopBits, this.parity);
                this.serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            }
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }    
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
        try{
            if(!(serialPort.isOpened())){
                serialPort.openPort();
            }
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }
    @Override
    public void disconnect(){
        try{
            if(serialPort.isOpened()){
            serialPort.closePort();
            }
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }
    @Override
    public void reconnect(){
        disconnect();
        connect();
    }
    @Override
    public boolean isConnected(){
        return this.serialPort.isOpened();
    }
    @Override
    public void addPortListener(SerialPortEventListener listener){       
        try{
            this.serialPort.addEventListener(listener, this.serialPort.MASK_RXCHAR);
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }
    @Override
    public void removePortListener(){       
        try{
            this.serialPort.removeEventListener();
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }
    @Override
    public String getReceivedData() {
        try{
            return this.serialPort.readString();
        }
        catch (SerialPortException e){
            e.printStackTrace();
            return "";
        }
    }
    @Override
    public void ClearBuffers(){
        try{
            this.serialPort.purgePort(serialPort.PURGE_RXCLEAR + serialPort.PURGE_TXCLEAR);
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }
    public void destroy(){
        try {
            this.serialPort.closePort();
        } catch (SerialPortException ex) {
            Logger.getLogger(CardScannerAdapterCOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


