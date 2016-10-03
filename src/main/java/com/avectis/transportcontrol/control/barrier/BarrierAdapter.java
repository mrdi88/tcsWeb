package com.avectis.transportcontrol.control.barrier;

/**
 * Created by vitaly on 30.08.2016.
 */
public interface BarrierAdapter {
    public void open();
    public void close();
    public boolean getState(int inputNumber);
}
