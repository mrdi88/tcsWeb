package com.avectis.transportcontrol.control.infotable;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Created by vitaly on 02.09.2016.
 */
public class InfoTableAdapterCOM implements InfoTableAdapter {

    private SerialPort serialPort; //Имя COM-порта
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;

    public InfoTableAdapterCOM() {
    }
    public InfoTableAdapterCOM(String portName) {
        try{
            this.serialPort = new SerialPort(portName);
            this.serialPort.openPort();
            this.serialPort.setParams(this.baudRate, this.dataBits, this.stopBits, this.parity);
            this.serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }

    private void Send(){
        System.out.println("Write to " + this.serialPort.getPortName());
    }
    private void Receive(){
        System.out.println("Read  of " + this.serialPort.getPortName());
    }

    @Override
    public void SendData(String[] data, int lineCount) {
        System.out.println(this.serialPort.getPortName()+ "; lineCount = "+lineCount);
        for (String str: data
             ) {
            System.out.println(str);
        }

    }


    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }
}
