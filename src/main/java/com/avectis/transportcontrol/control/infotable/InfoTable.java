package com.avectis.transportcontrol.control.infotable;


import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by vitaly on 02.09.2016.
 */
public class InfoTable {
    public String name;
    private LocalDateTime dateLastUpdate;
    private final int lineCount;
    private InfoTableAdapter infoTableAdapter;

    public InfoTable(String name, int lineCount, InfoTableAdapter infoTableAdapter) {
        this.name = name;
        this.lineCount = lineCount;
        this.infoTableAdapter = infoTableAdapter;
    }

    public LocalDateTime getDateLastUpdate() {
        System.out.println("InfoTable name:="+this.name + " cmd:=getDateLastUpdate" );
        return dateLastUpdate;
    }

    private void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public void SendData(String [] data){
        System.out.println("InfoTable name:="+this.name + " cmd:=SendData("+data+")");
        this.infoTableAdapter.SendData(data, lineCount);
        LocalDateTime time = LocalDateTime.now(ZoneId.of("Europe/Minsk"));
        setDateLastUpdate(time);
    }
}
