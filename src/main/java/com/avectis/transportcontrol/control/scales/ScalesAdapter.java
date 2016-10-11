package com.avectis.transportcontrol.control.scales;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 * Created by vitaly on 30.08.2016.
 */
public interface ScalesAdapter {
    public int getWeight() throws ConnectionFailException;
}
