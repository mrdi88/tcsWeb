package com.avectis.transportcontrol.control.trafficLight;

/**
 * Created by vitaly on 30.08.2016.
 */
public class TrafficLightAdapterTest implements TrafficLightAdapter {
    
    private LightState ls;
    
    public TrafficLightAdapterTest() {
    }

    @Override
    public void TurnGreen() {
        ls=LightState.GREEN;
    }

    @Override
    public void TurnRed() {
        ls=LightState.RED;
    }

    @Override
    public LightState GetState() {
        return ls;
    }

    

}
