package com.avectis.transportcontrol.control.infotable;

import com.avectis.transportcontrol.exception.ConnectionFailException;
import java.util.List;

/**
 *
 * @author Ivan
 */
public interface InfoTableAdapter {
    public void SendData(List<String> data) throws ConnectionFailException;
    public void SetBrightness(int brightness) throws ConnectionFailException;
}
