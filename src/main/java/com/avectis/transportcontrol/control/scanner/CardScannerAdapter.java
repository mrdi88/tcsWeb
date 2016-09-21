package com.avectis.transportcontrol.control.scanner;

import jssc.SerialPortEventListener;

/**
 *
 * @author Ivan
 */
public interface CardScannerAdapter {
    public void connect();
    public void disconnect();
    public void reconnect();
    
    public boolean isConnected();
    
    public void addPortListener(SerialPortEventListener listener);
    public void removePortListener();
    public String getReceivedData();
    
    public void ClearBuffers();
}
