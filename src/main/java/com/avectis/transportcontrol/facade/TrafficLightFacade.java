package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.control.trafficLight.TrafficLight;

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

    /*Поиск светофора по ID*/
    public TrafficLight GetElementById( String id){
        for (TrafficLight trafficLight: trafficLightCollection) {
            if(id.equals(trafficLight.name)){
                return trafficLight;
            }
        }
        return null;
    }
}
