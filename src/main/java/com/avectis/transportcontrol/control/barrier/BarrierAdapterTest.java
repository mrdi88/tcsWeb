package com.avectis.transportcontrol.control.barrier;

/**
 * Created by vitaly on 30.08.2016.
 */
public class BarrierAdapterTest implements BarrierAdapter {

    public BarrierAdapterTest(String portName){
    }
    public BarrierAdapterTest() {
    }
    @Override //Получения состояния щлагбаума
    public void open() {
    }
    @Override//Открытие шлагбаума
    public void close() {
    }

    @Override
    public boolean getState(int inputNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
