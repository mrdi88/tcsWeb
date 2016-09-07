package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.control.scanner.Scanner;

import java.util.Collection;

/**
 * Created by vitaly on 06.09.2016.
 */
public class ScannerFacade {
    private Collection<Scanner> scannerCollection;

    public void setScannerCollection(Collection<Scanner> scannerCollection){
        this.scannerCollection = scannerCollection;
    }
    public Collection<Scanner> getScannerCollection() {
        return null;
    }

    /*Получение данных со сканера по ID*/
    public String GetData(String id){
        Scanner scanner = GetElementById(id);
        if(null != scanner) return scanner.GetData();
        else {
            System.out.println("Scanner's not found");
            return null;
        }
    }

    /*Поиск сканера по ID*/
    private Scanner GetElementById( String id){
        for (Scanner scanner: scannerCollection) {
            if(id.equals(scanner.name)){
                return scanner;
            }
        }
        return null;
    }

}
