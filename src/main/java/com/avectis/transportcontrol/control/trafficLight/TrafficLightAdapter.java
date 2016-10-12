package com.avectis.transportcontrol.control.trafficLight;

/**
 * Created by vitaly on 30.08.2016.
 */
public interface TrafficLightAdapter {
    public void TurnGreen();
    public void TurnRed();
    public LightState GetState();
}
