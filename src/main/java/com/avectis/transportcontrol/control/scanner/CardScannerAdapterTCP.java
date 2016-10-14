/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.control.scanner;
import com.avectis.transportcontrol.exception.ConnectionFailException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Ivan
 */
public class CardScannerAdapterTCP implements CardScannerAdapter{
    private String ipAddr;
    private int port;
    
    private Socket socket; 
    private DataListener externalDataListener;
    private Thread socketThread;
    private boolean socketThreadRun = true;
            
    public CardScannerAdapterTCP(){
    }
    public CardScannerAdapterTCP(String IP, int port){
        this.ipAddr = IP;
        this.port = port; 
        try {
            init();
        } catch (ConnectionFailException ex) {
            ex.printStackTrace();
        }
    }
    public void init() throws ConnectionFailException{    
        try{
            InetAddress ipAddress = InetAddress.getByName(this.ipAddr);
            this.socket = new Socket(ipAddress,this.port);
            this.socket.setSoTimeout(0);

            socketThread = new Thread(){
                @Override
                public void run(){
                    try{
                        InputStream sin = socket.getInputStream();
                        OutputStream sout = socket.getOutputStream();
                        DataInputStream in = new DataInputStream(sin);

                        while(socketThreadRun){
                            String line = in.readLine();
                            externalDataListener.onDataReceive(line);
                        }
                    }
                    catch(IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            };
            this.socketThread.start();
        }
        catch(UnknownHostException ex)
        {
            throw new ConnectionFailException("Unable to find remote host ("+this.ipAddr+":"+this.port+").\n" + ex.getMessage());
        }
        catch(SocketException ex)
        {
            throw new ConnectionFailException("Unable to create remote host connection ("+this.ipAddr+":"+this.port+").\n" + ex.getMessage());
        }
        catch(IOException ex)
        {
            throw new ConnectionFailException("Unable to connect remote host ("+this.ipAddr+":"+this.port+").\n" + ex.getMessage());
        }                        
    } 
          
    public void onDestroy(){
        try {
            socketThreadRun = false;
            this.socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
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
}

