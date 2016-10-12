package com.avectis.transportcontrol.control.trafficLight;

/**
 * Created by vitaly on 02.09.2016.
 */
public class TrafficLight {
    
    private LightState ls;
    
    public String   name;
    private TrafficLightAdapter trafficLightAdapter;

    public void setName(String name) {
        this.name = name;
    }

    public void setTrafficLightAdapter(TrafficLightAdapter trafficLightAdapter) {
        this.trafficLightAdapter = trafficLightAdapter;
    }

    public TrafficLight() {
    }

    public void TurnGreen(){
        this.trafficLightAdapter.TurnGreen();
    }
    public void TurnRed(){
        this.trafficLightAdapter.TurnRed();
    }
    public LightState GetState(){
        return ls;
    }
    public void updateState(){
        ls=this.trafficLightAdapter.GetState();
    }

}
