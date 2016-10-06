package com.avectis.transportcontrol.control.infotable;
import java.net.*;

/**
 *
 * @author Ivan
 */
public class InfoTableAdapterTest implements InfoTableAdapter {
    
    private String ipAddr;
    private int port;
    private int InfoTableAddress;
    private Socket socket;
    
    public InfoTableAdapterTest(){} 
    public InfoTableAdapterTest(String IP, int port, int address){}
    @Override
    public void SendData(String[] data) {            
    }
    @Override
    public void SetBrightness(int brightness){
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
    public int getInfoTableAddress() {
        return InfoTableAddress;
    }
    public void setInfoTableAddress(int InfoTableAddress) {
        this.InfoTableAddress = InfoTableAddress;
    }
}
