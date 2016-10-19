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
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Ivan
 */
public class CardScannerAdapterTCP implements CardScannerAdapter{
    
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CardScannerAdapterTCP.class.getName());
    
    private String ipAddr;
    private int port;
    
    private Socket socket; 
    private Socket watchDogSocket; 
    private DataListener externalDataListener;
    private Thread socketThread;
    private boolean socketThreadRun = true;
    private InetAddress ipAddress;
            
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
            this.ipAddress = InetAddress.getByName(this.ipAddr);
            this.socket = new Socket(ipAddress,this.port);
            this.socket.setSoTimeout(1000);

            socketThread = new Thread(){
                @Override
                public void run(){
                    while(socketThreadRun){
                        try{
                            InputStream sin = socket.getInputStream();
                            DataInputStream in = new DataInputStream(sin);
                            String line = in.readLine();
                            externalDataListener.onDataReceive(line);                                
                        }
                        catch(IOException ex){
                            try{
                                if (!ipAddress.isReachable(100)){
                                    System.out.println("UNABLE TO CONNECT REMOTE SERVER");
                                };
                                watchDogSocket = new Socket(ipAddress,port);
                                watchDogSocket.setSoTimeout(1000);
                                System.out.println("RECONNECTING");
                                watchDogSocket.close();
                                socket=watchDogSocket;
                            }
                            catch(Exception e){
                            }
                        }
                    }
                }
            };
            this.socketThread.start();
        }
        catch(UnknownHostException ex)
        {
            logger.error("init: "+ex.getMessage());
        }
        catch(SocketException ex)
        {
            logger.error("init: "+ex.getMessage());
        }
        catch(IOException ex)
        {
            logger.error("init: "+ex.getMessage());
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

