package com.avectis.transportcontrol.control.relay;

import com.avectis.transportcontrol.exception.ConnectionFailException;

/**
 * Created by vitaly on 24.08.2016.
 */
public class Relay {

    private String   name;
    private RelayAdapter relayAdapter;

    public Relay(){}
    public Relay(String barrierName, RelayAdapter barrierAdapter){
        this.name = barrierName;
        this.relayAdapter = relayAdapter;
    }
    public void pulseDO1() throws ConnectionFailException{
        this.relayAdapter.pulseDO1();
    }
    public void pulseDO2() throws ConnectionFailException{
        this.relayAdapter.pulseDO2();
    }
    public void turnOnDO1() throws ConnectionFailException{
        this.relayAdapter.turnOnDO1();
    }
    public void turnOnDO2() throws ConnectionFailException{
        this.relayAdapter.turnOnDO2();
    }
    public void turnOffDO1() throws ConnectionFailException{
        this.relayAdapter.turnOffDO1();
    }
    public void turnOffDO2() throws ConnectionFailException{
        this.relayAdapter.turnOffDO2();
    }
    public Boolean GetStateDI_0() throws ConnectionFailException{
        return this.relayAdapter.getState(0);
    }
    public Boolean GetStateDI_1() throws ConnectionFailException{
        return this.relayAdapter.getState(1);
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public RelayAdapter getRelayAdapter() {
        return relayAdapter;
    }
    public void setRelayAdapter(RelayAdapter relayAdapter) {
        this.relayAdapter = relayAdapter;
    }
}
