package com.avectis.transportcontrol.control.barrier;

/**
 * Created by vitaly on 24.08.2016.
 */
public class Barrier {

    public String   name;
    private boolean  state;
    private BarrierAdapter barrierAdapter;

    public Barrier(String barrierName, BarrierAdapter barrierAdapter){
        this.name = barrierName;
        this.state = false;
        this.barrierAdapter = barrierAdapter;
    }

    public void Open(){
        System.out.println("Barrier name:="+this.name + " cmd:=Open" );
        this.barrierAdapter.Open();
    }
    public void Close(){
        System.out.println("Barrier name:="+this.name + " cmd:=Close" );
        this.barrierAdapter.Close();
    }
    public Boolean GetState(){
        System.out.println("Barrier name:="+this.name + " cmd:=GetState" );
        Boolean state=false;
        this.barrierAdapter.GetState();
        return state;
    }












}
