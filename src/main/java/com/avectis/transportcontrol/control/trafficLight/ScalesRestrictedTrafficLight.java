package com.avectis.transportcontrol.control.trafficLight;

import com.avectis.transportcontrol.control.scales.Scales;

/**
 * Created by Dima on 13.10.2016.
 */
public class ScalesRestrictedTrafficLight extends TrafficLight{
    
    private Boolean restriction=false; //prevent green signal befor restriction missed
    private volatile boolean isWaitingRestriction=false;
    private Scales scales;
    private int restrictionValue=100;

    
    public void setRestrictionValue(int restrictionValue) {
        this.restrictionValue = restrictionValue;
    }

    public void setScales(Scales scales) {
        this.scales = scales;
    }

    public ScalesRestrictedTrafficLight() {
    }
    @Override 
    public void TurnGreen(){
        if (isWaitingRestriction) return;
        //check weight
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                setRestrinction();
                while (true){
                    try {
                        if(scales.getWeight()<restrictionValue){
                            break;
                        }else{
                           Thread.sleep(1000);
                        }  
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(ScalesRestrictedTrafficLight.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                resetRestrinction();
            }
        };
        thread1.start();
        //Turn Green
        Thread thread2 = new Thread(){
            @Override
            public void run(){
                try {
                    //restriction
                    synchronized(restriction){
                        if (restriction){
                            isWaitingRestriction=true;
                            restriction.wait();
                            isWaitingRestriction=false;
                        }
                        nativeTrurnGreen();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    isWaitingRestriction=false;
                    //Logger.getLogger(ScalesRestrictedTrafficLight.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        thread2.start();
    }
    private void nativeTrurnGreen(){
        super.TurnGreen();
    }
    public void setRestrinction(){
        synchronized(restriction){
            restriction=true;
        }
    }
    public void resetRestrinction(){
        synchronized(restriction){
            restriction=false;
            restriction.notifyAll();
        }
    }
}
