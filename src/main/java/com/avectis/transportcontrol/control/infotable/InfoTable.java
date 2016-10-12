package com.avectis.transportcontrol.control.infotable;

import com.avectis.transportcontrol.exception.ConnectionFailException;
import java.util.List;


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
    
    public void SendData(List<String> textArray) throws ConnectionFailException{
        infoTableAdapter.SendData(textArray);
    }
    public void SetBrightness(int bv) throws ConnectionFailException{
        infoTableAdapter.SetBrightness(bv);
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

