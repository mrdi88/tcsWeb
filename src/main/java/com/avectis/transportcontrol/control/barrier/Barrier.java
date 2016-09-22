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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public BarrierAdapter getBarrierAdapter() {
        return barrierAdapter;
    }

    public void setBarrierAdapter(BarrierAdapter barrierAdapter) {
        this.barrierAdapter = barrierAdapter;
    }

    public void Open(){
        System.out.println("Barrier name:="+this.name + " cmd:=Open" );
        this.barrierAdapter.open();
    }
    public void Close(){
        System.out.println("Barrier name:="+this.name + " cmd:=Close" );
        this.barrierAdapter.close();
    }
    public void updateState(){
        this.state = this.barrierAdapter.getState();
    }












}
