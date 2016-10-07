package com.avectis.transportcontrol.control.barrier;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 * Created by vitaly on 30.08.2016.
 */
public interface BarrierAdapter {
    public void open()throws ConnectionFailException;
    public void close()throws ConnectionFailException;
    public boolean getState(int inputNumber) throws ConnectionFailException;
}
