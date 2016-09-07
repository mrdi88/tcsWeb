package com.avectis.transportcontrol.control.traffic_light;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Created by vitaly on 30.08.2016.
 */
public class TrafficLightAdapterCOM implements TrafficLightAdapter {

    private SerialPort   serialPort; //Имя COM-порта
    private int baudRate;
    private int dataBits;
    private int stopBits;
    private int parity;

    public TrafficLightAdapterCOM(){ }
    public TrafficLightAdapterCOM(String portName){
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

    @Override //Получения состояния щлагбаума
    public void TurnOn() {
        Send();
    }
    @Override//Открытие шлагбаума
    public void TurnOff() {
        Send();
    }
    @Override//Закрытие шлагбаума
    public Boolean GetState() {
        Boolean response=false;
        Send();
        Receive();
        return response;
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
