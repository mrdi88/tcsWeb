package com.avectis.transportcontrol.control.scales;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 * Created by vitaly on 30.08.2016.
 */
public class Scales implements ScalesAdapter{
    
    private String name;
    private ScalesAdapter scalesAdapter;

    public Scales() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setScalesAdapter(ScalesAdapter scalesAdapter) {
        this.scalesAdapter = scalesAdapter;
    }
    
    public int getWeight() throws ConnectionFailException{
        return scalesAdapter.getWeight();
    }
}
