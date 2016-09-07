package com.avectis.transportcontrol.control.traffic_light;

/**
 * Created by vitaly on 30.08.2016.
 */
public interface TrafficLightAdapter {
    public void TurnOn();
    public void TurnOff();
    public Boolean GetState();
}
