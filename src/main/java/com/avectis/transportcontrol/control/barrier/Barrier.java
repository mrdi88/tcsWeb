package com.avectis.transportcontrol.control.barrier;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 * Created by vitaly on 24.08.2016.
 */
public class Barrier {

    private String   name;
    private BarrierAdapter barrierAdapter;

    public Barrier(){}
    public Barrier(String barrierName, BarrierAdapter barrierAdapter){
        this.name = barrierName;
        this.barrierAdapter = barrierAdapter;
    }

    public void Open() throws ConnectionFailException{
        this.barrierAdapter.open();
    }
    public void Close() throws ConnectionFailException{
        this.barrierAdapter.close();
    }
    public Boolean GetStateDI_0() throws ConnectionFailException{
        return this.barrierAdapter.getState(0);
    }
    public Boolean GetStateDI_1() throws ConnectionFailException{
        return this.barrierAdapter.getState(1);
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BarrierAdapter getBarrierAdapter() {
        return barrierAdapter;
    }
    public void setBarrierAdapter(BarrierAdapter barrierAdapter) {
        this.barrierAdapter = barrierAdapter;
    }
}
