package com.avectis.transportcontrol.control.relay;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 * Created by vitaly on 30.08.2016.
 */
public interface RelayAdapter {
    public void turnOnDO1() throws ConnectionFailException;
    public void turnOnDO2() throws ConnectionFailException;
    public void turnOffDO1() throws ConnectionFailException;
    public void turnOffDO2() throws ConnectionFailException;
    public void pulseDO1() throws ConnectionFailException;
    public void pulseDO2() throws ConnectionFailException;
    public boolean getState(int inputNumber) throws ConnectionFailException;
}
