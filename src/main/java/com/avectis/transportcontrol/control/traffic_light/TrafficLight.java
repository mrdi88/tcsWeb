package com.avectis.transportcontrol.control.traffic_light;

/**
 * Created by vitaly on 02.09.2016.
 */
public class TrafficLight {

    public String   name;
    private boolean  state;
    private TrafficLightAdapter trafficLightAdapter;

    public TrafficLight(String trafficLightName, TrafficLightAdapter trafficLight){
        this.name = trafficLightName;
        this.state = false;
        this.trafficLightAdapter = trafficLight;
    }

    public void TurnOn(){
        System.out.println("TrafficLight name:="+this.name + " cmd:=TurnOn" );
        this.trafficLightAdapter.TurnOn();
    }
    public void TurnOff(){
        System.out.println("TrafficLight name:="+this.name + " cmd:=TurnOff" );
        this.trafficLightAdapter.TurnOff();
    }
    public Boolean GetState(){
        System.out.println("TrafficLight name:="+this.name + " cmd:=GetState" );
        return this.trafficLightAdapter.GetState();
    }


}
