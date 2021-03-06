package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.control.barrier.Barrier;
import com.avectis.transportcontrol.exception.ConnectionFailException;

import java.util.Collection;

/**
 * Created by vitaly on 06.09.2016.
 */
public class BarrierFacade {

    private Collection<Barrier> barrierCollection;

    public void setBarrierCollection(Collection<Barrier> barrierCollection){
        this.barrierCollection = barrierCollection;
    }
    public Collection<Barrier> getBarrierCollection() {
        return this.barrierCollection;
    }

    /*Отрытие шлагбаума по ID*/
    public void Open(String id) throws ConnectionFailException{
        Barrier barrier = GetElementById(id);
        if(null != barrier)barrier.Open();
        else System.out.println("Barrier's not found");
    }

    /*Закрытие шлагбаума по ID*/
    public void Close(String id) throws ConnectionFailException{
        Barrier barrier = GetElementById(id);
        if(null != barrier)barrier.Close();
        else System.out.println("Barrier's not found");
    }

    /*Получение состояния шлагбаума по ID*/
    public Boolean GetState(String id) throws ConnectionFailException{
        Barrier barrier = GetElementById(id);
        if(null != barrier)return barrier.GetStateDI_0();
        else {
            System.out.println("Barrier's not found");
            return null;
        }
    }

    /*Поиск шлагбаума по ID*/
    public Barrier GetElementById( String id){
        for (Barrier barrier: barrierCollection) {
            if(id.equals(barrier.getName())){
               return barrier;
            }
        }
        return null;
    }



}
