package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.control.traffic_light.TrafficLight;

import java.util.Collection;

/**
 * Created by vitaly on 06.09.2016.
 */
public class TrafficLightFacade {
    private Collection<TrafficLight> trafficLightCollection;

    public void setTrafficLightsCollection(Collection<TrafficLight> trafficLightCollection){
        this.trafficLightCollection = trafficLightCollection;
    }
    public Collection<TrafficLight> getTrafficLightCollection() {
        return this.trafficLightCollection;
    }

    /*Включение светофора по ID*/
    public void TurnOn(String id){
        TrafficLight trafficLight = GetElementById(id);
        if(null != trafficLight)trafficLight.TurnOn();
        else System.out.println("Barrier's not found");
    }

    /*Выключение светофора по ID*/
    public void TurnOff(String id){
        TrafficLight trafficLight = GetElementById(id);
        if(null != trafficLight)trafficLight.TurnOff();
        else System.out.println("Barrier's not found");
    }

    /*Получение состояния светофора по ID*/
    public Boolean GetState(String id){
        TrafficLight trafficLight = GetElementById(id);
        if(null != trafficLight)return trafficLight.GetState();
        else {
            System.out.println("Barrier's not found");
            return null;
        }
    }



    /*Поиск светофора по ID*/
    private TrafficLight GetElementById( String id){
        for (TrafficLight trafficLight: trafficLightCollection) {
            if(id.equals(trafficLight.name)){
                return trafficLight;
            }
        }
        return null;
    }
}
