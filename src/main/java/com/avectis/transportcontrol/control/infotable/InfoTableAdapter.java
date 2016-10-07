package com.avectis.transportcontrol.control.infotable;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 *
 * @author Ivan
 */
public interface InfoTableAdapter {
    public void SendData(String[] data) throws ConnectionFailException;
    public void SetBrightness(int brightness) throws ConnectionFailException;
}
