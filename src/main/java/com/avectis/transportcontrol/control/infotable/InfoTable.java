package com.avectis.transportcontrol.control.infotable;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 *
 * @author Ivan
 */
public class InfoTable {
    private String name;
    private InfoTableAdapter infoTableAdapter;
    
    public InfoTable(){}
    public InfoTable(String name, InfoTableAdapter adapter){
        this.name = name;
        this.infoTableAdapter = adapter;
    }
    
    public void SendData(String[] textArray){
        infoTableAdapter.SendData(textArray);
    }
    public void SetBrightness(int v){
        infoTableAdapter.SetBrightness(v);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public InfoTableAdapter getInfoTableAdapter() {
        return infoTableAdapter;
    }
    public void setInfoTableAdapter(InfoTableAdapter infoTableAdapter) {
        this.infoTableAdapter = infoTableAdapter;
    }
    
    
}
