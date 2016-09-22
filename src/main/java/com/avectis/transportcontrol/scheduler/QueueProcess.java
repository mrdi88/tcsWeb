/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.scheduler;

import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.QueueNameView;
import java.util.List;

/**
 *
 * @author Dima
 */
public class QueueProcess {
    private QueueFacade queueFacade;
    private List<QueueProperties> queuePropList;

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    public void setQueuePropList(List<QueueProperties> queuePropList) {
        this.queuePropList = queuePropList;
    }

    public QueueProcess() {
    }
    public void init(){ //init queue
        List<QueueNameView> qnList = queueFacade.getQueueNameList();
        boolean existFlag;
        for (QueueProperties qp:queuePropList){
            existFlag=false;
            for (QueueNameView qn:qnList){
                if (qp.getName().equals(qn.getName())) existFlag=true;
            }
            if (!existFlag){ //create queue
                
            }
        }
    }
    public void doJob(){
        //
        System.out.println("QueueProcess doJob called");
    }
}
