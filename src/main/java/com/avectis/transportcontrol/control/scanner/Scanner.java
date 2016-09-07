package com.avectis.transportcontrol.control.scanner;

/**
 * Created by vitaly on 02.09.2016.
 */
public class Scanner {
    public String name;
    private ScannerAdapter scannerAdapter;
    public Scanner(String scannerName, ScannerAdapter scannerAdapter){
        this.name = scannerName;
        this.scannerAdapter = scannerAdapter;
    }
    public String GetData(){
        return this.scannerAdapter.GetData();
    }

    public Scanner() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScannerAdapter(ScannerAdapter scannerAdapter) {
        this.scannerAdapter = scannerAdapter;
    }
    
}
