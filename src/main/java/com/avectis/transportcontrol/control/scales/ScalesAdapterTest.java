package com.avectis.transportcontrol.control.scales;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 * Created by vitaly on 30.08.2016.
 */
public class ScalesAdapterTest implements ScalesAdapter{
    
    public int getWeight() throws ConnectionFailException{
        return (int)(30000*Math.random());
    }
    public void init(){
        
    }
}
