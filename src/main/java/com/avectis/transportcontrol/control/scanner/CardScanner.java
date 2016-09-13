package com.avectis.transportcontrol.control.scanner;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import java.util.regex.Matcher;  
import java.util.regex.Pattern; 


/**
 *
 * @author Ivan
 */
public class CardScanner {
    
    private String name;
    private CardScannerAdapter scannerAdapter;
    private CardScannerPortListener adapterListener=new CardScannerPortListener();
    private ArrayList<CardScannerListener> listeners=new ArrayList();
    
    public CardScanner() { }

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
        if (this.scannerAdapter!=null){
            this.scannerAdapter.removePortListener();
        }
        this.scannerAdapter = scannerAdapter;
        this.scannerAdapter.addPortListener(this.adapterListener);
    }
    
    public void addListener(CardScannerListener listener) {
      synchronized(this.listeners){
        this.listeners.add(listener);
      }
    }
    public void removeListener(CardScannerListener listener) {
        synchronized(this.listeners){
            this.listeners.remove(listener);
        }
    }
    private void notifyListeners(String cardNHex, String cardNDec) {
        ArrayList<CardScannerListener> tempListeners=new ArrayList();
        synchronized(this.listeners){
            for(CardScannerListener listener : listeners){
                tempListeners.add(listener);
            }
        }
        //System.out.println("notify "+tempListeners.size()+" listeners");
        for(CardScannerListener listener : tempListeners){
                listener.onCardLogined(cardNHex, cardNDec);
                System.out.println("notify "+cardNHex+" "+cardNDec);
        }
    }
    
    public class CardScannerPortListener implements SerialPortEventListener {
    @Override 
    public void serialEvent(SerialPortEvent eventData) {
        //System.out.println("Data received");
        if(eventData.getEventValue() > 2)
        {
            try {
                Thread.sleep(10);
                String tempStr = scannerAdapter.getReceivedData();
                //System.out.println(tempStr +"  "+ tempStr.length());
                System.out.println(tempStr);
                if(tempStr != null )
                {
                    String tempNumberHEX = null;
                    String tempNumberDEC = null;
                    String p1="\\[[A-F0-9]{1,}\\]";
                    String p2="[0-9]{3}\\,[0-9]{5}";
                    Pattern p = Pattern.compile(p1);
                    Matcher m = p.matcher(tempStr);
                    if(m.find()){
                        tempNumberHEX = m.group(0).substring(1, m.group(0).length() - 1);
                    }
                    
                    p = Pattern.compile(p2);
                    m = p.matcher(tempStr);
                    if(m.find()){
                        tempNumberDEC = m.group(0);
                    }
                    //System.out.println("Listeners notifyed");
                    if(tempNumberHEX != null && tempNumberDEC != null)
                        notifyListeners(tempNumberHEX, tempNumberDEC);
                }
            }
            //scannerAdapter.ClearBuffers();
            catch (InterruptedException ex) {
                Logger.getLogger(CardScanner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        }
    }
}
