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
    @Override//Закрытие шлагбаума
    public boolean getState() {
        return false;
    }
}
