package com.avectis.transportcontrol.control.barrier;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Created by vitaly on 30.08.2016.
 */
public class BarrierAdapterCOM implements BarrierAdapter {

    public SerialPort   serialPort; //Имя COM-порта
    public  String      response="";//Ответ COM-порта
    private String baudRate;
    private String dataBits;
    private String stopBits;
    private String parity;

    public BarrierAdapterCOM(String portName){
        try{
            this.serialPort = new SerialPort(portName);
            this.serialPort.openPort();
            this.serialPort.setParams(9600, 8, 1, 0);
            this.serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
        }
        catch (SerialPortException e){
            e.printStackTrace();
        }
    }

    public BarrierAdapterCOM() {
    }

    private void Send(){
        System.out.println("Write to " + this.serialPort.getPortName());
    }
    private void Receive(){
        System.out.println("Read  of " + this.serialPort.getPortName());
    }
    @Override //Получения состояния щлагбаума
    public void Open() {
        Send();
    }

    @Override//Открытие шлагбаума
    public void Close() {
        Send();
    }

    @Override//Закрытие шлагбаума
    public void GetState() {
        Send();
        Receive();
    }

    public void setBaudRate(String baudRate) {
        this.baudRate = baudRate;
    }


    public void setDataBits(String dataBits) {
        this.dataBits = dataBits;
    }



    public void setStopBits(String stopBits) {
        this.stopBits = stopBits;
    }



    public void setParity(String parity) {
        this.parity = parity;
    }


}
