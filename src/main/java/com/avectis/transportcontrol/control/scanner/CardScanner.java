package com.avectis.transportcontrol.control.scanner;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;  
import java.util.regex.Pattern; 

/**
 *
 * @author Ivan
 */
public class CardScanner implements DataListener {
    
    private String name;
    private CardScannerAdapter scannerAdapter;
    private ArrayList<CardScannerListener> listeners=new ArrayList();
    
    public CardScanner() {}
     
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
        for(CardScannerListener listener : tempListeners){
                listener.onCardLogined(cardNHex, cardNDec);
                System.out.println("notify "+cardNHex+" "+cardNDec);
        }
    }
    
    @Override
    public void onDataReceive(String data){
        if(data != null && data.length() > 2)
        {
            try {
                if(data != null )
                {
                    String tempNumberHEX = null;
                    String tempNumberDEC = null;
                    String p1="\\[[A-F0-9]{1,}\\]";
                    String p2="[0-9]{3}\\,[0-9]{5}";
                    Pattern p = Pattern.compile(p1);
                    Matcher m = p.matcher(data);
                    if(m.find()){
                        tempNumberHEX = m.group(0).substring(1, m.group(0).length() - 1);
                    }
                    
                    p = Pattern.compile(p2);
                    m = p.matcher(data);
                    if(m.find()){
                        tempNumberDEC = m.group(0);
                    }                    
                    if(tempNumberHEX != null && tempNumberDEC != null)
                        notifyListeners(tempNumberHEX, tempNumberDEC);
                }
            }
            catch (Exception ex) {
                Logger.getLogger(CardScanner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        this.scannerAdapter = scannerAdapter;
        this.scannerAdapter.addDataListener(this);
    }
}
