package com.avectis.transportcontrol.control.scanner;

import java.util.ArrayList;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;


/**
 *
 * @author Ivan
 */
public class CardScanner {
    
    private String name;
    private CardScannerAdapter scannerAdapter;
    private CardScannerPortListener adapterListener;
    private ArrayList<CardScannerListener> listeners;
    
    public CardScanner(String scannerName, CardScannerAdapter adapter) {
        this.name = scannerName;
        this.scannerAdapter = adapter;
        this.adapterListener = new CardScannerPortListener();
        this.scannerAdapter.addPortListener(this.adapterListener);
        this.listeners = new ArrayList();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public CardScannerAdapter getScannerAdapter() {
        return scannerAdapter;
    }
    public void setScannerAdapter(CardScannerAdapter scannerAdapter) {
        this.scannerAdapter.removePortListener();
        this.scannerAdapter = scannerAdapter;
        this.scannerAdapter.addPortListener(this.adapterListener);
    }
    
    public void addListener(CardScannerListener listener) {
      this.listeners.add(listener);
    }
    public void removeListener(CardScannerListener listener) {
      this.listeners.remove(listener);
    }
    private void notifyListeners(String cardNHex, String cardNDec) {
        for(CardScannerListener listener : listeners){
            listener.onCardLogined(cardNHex, cardNDec);
        }
    }
    private void notifyListeners() {
        for(CardScannerListener listener : listeners){
            listener.onCardRemoved();
        }
    }
    
    public class CardScannerPortListener implements SerialPortEventListener {
    @Override 
    public void serialEvent(SerialPortEvent eventData) {
        System.out.println("Data received");
        if(eventData.getEventValue() > 2)
        {
            String tempStr = scannerAdapter.getReceivedData();
            System.out.println(tempStr +"  "+ tempStr.length());
            if(tempStr.length() >= 39)
            {
                String tempNumberHEX = tempStr.substring(7, 15);
                String tempNumberDEC = tempStr.substring(30, 39);
                System.out.println("Listeners notifyed");
                notifyListeners(tempNumberHEX, tempNumberDEC);

            }
            else if(tempStr.contains("No Card "))
            {
                notifyListeners();
            }
            else
            {
                
            }
            
        }    
        scannerAdapter.ClearBuffers();
        }
    }
    
}
