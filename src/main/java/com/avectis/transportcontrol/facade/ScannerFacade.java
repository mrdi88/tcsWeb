package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.control.scanner.CardScanner;


import java.util.Collection;

/**
 * Created by vitaly on 06.09.2016.
 */
public class ScannerFacade {
    private Collection<CardScanner> scannerCollection;

    public void setScannerCollection(Collection<CardScanner> scannerCollection){
        this.scannerCollection = scannerCollection;
    }
    /*Поиск сканера по ID*/
    public CardScanner getElementById( String id){
        for (CardScanner scanner: scannerCollection) {
            if(id.equals(scanner.getName())){
                return scanner;
            }
        }
        return null;
    }

}
