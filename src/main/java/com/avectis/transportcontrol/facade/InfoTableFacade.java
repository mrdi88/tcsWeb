package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.control.infotable.InfoTable;
import com.avectis.transportcontrol.exception.ConnectionFailException;

import java.util.Collection;
import java.util.List;

/**
 * Created by vitaly on 06.09.2016.
 */
public class InfoTableFacade {

    private Collection<InfoTable> infoTableCollection;

    public void setInfoTableCollection(Collection<InfoTable> infoTableCollection){
        this.infoTableCollection = infoTableCollection;
    }

    public Collection<InfoTable> getInfoTableCollection() {
        return this.infoTableCollection;
    }

    /*Отправка данных на табло по ID*/
    public void SendData(List<String> data, String id) throws ConnectionFailException{
        InfoTable infotable = GetElementById(id);
        if(null != infotable) infotable.SendData(data);
        else {
            System.out.println("InfoTable's not found");
        }
    }

    /*Получение времени последнего обновления табло по ID*/
//    public LocalDateTime getDateLastUpdate( String id){
//        InfoTable infotable = GetElementById(id);
//        if(null != infotable)return infotable.getDateLastUpdate();
//        else {
//            System.out.println("InfoTable's not found");
//            return null;
//        }
//    }

    /*Поиск табло по ID*/
    public InfoTable GetElementById( String id){
        for (InfoTable infoTable: infoTableCollection) {
            if(id.equals(infoTable.getName())){
                return infoTable;
            }
        }
        return null;
    }
}
