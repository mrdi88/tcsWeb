/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.scheduler;

import com.avectis.transportcontrol.util.QueueType;
import com.avectis.transportcontrol.exception.WrongParamException;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.QueueNameView;
import com.avectis.transportcontrol.view.QueueView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dima
 */
public class QueueProcess {
    private QueueFacade queueFacade;
    private List<QueueProperties> queuePropList;
    private QueueProperties bufferQueueProp;
    private QueueProperties InfoQueueProp;

    public void setInfoQueueProp(QueueProperties InfoQueueProp) {
        this.InfoQueueProp = InfoQueueProp;
    }

    public void setBufferQueueProp(QueueProperties bufferQueueProp) {
        this.bufferQueueProp = bufferQueueProp;
    }

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    public void setQueuePropList(List<QueueProperties> queuePropList) {
        this.queuePropList = queuePropList;
    }

    public QueueProcess() {
    }
    public void init() throws WrongParamException{ //init queue
        //check params
        if (bufferQueueProp==null || !bufferQueueProp.getType().equals(QueueType.BUFFER)){
            throw new WrongParamException("bufferQueueProp wrong params: " + bufferQueueProp);
        }
        if (InfoQueueProp==null || !InfoQueueProp.getType().equals(QueueType.INFO)){
            throw new WrongParamException("InfoQueueProp wrong params: " + InfoQueueProp);
        }
        //get queue simple list
        List<QueueNameView> qnList = queueFacade.getQueueNameList();
        boolean existFlag;
        //add new queues
        for (QueueProperties qp:queuePropList){
            existFlag=false;
            for (QueueNameView qn:qnList){
                if (qp.getName().equals(qn.getName())){
                    existFlag=true;
                    qp.setId(qn.getId());
                }
            }
            if (!existFlag){ //create queue
                QueueView qv=new QueueView();
                qv.setName(qp.getName());
                qv.setType(qp.getType());
                qv.setCards(new ArrayList());
                qv.setId(queueFacade.addQueue(qv));
                qp.setId(qv.getId());
                System.out.println("QueueProcess queue add "+ qv);
            }
        }
        //delete not exist queues
        QueueView q;
        qnList = queueFacade.getQueueNameList();
        for (QueueNameView qn:qnList){
            existFlag=false;
            for (QueueProperties qp:queuePropList){
                if (qp.getName().equals(qn.getName())) existFlag=true;
            }
            if (!existFlag){
                q=queueFacade.getQueue(qn.getId());
                queueFacade.delete(q);
                System.out.println("QueueProcess queue deleted "+ q);
            }    
        }
    }
    public void doJob(){
        //move from buffer to queue if the length is less than max
        QueueView qv = queueFacade.getQueueByName(bufferQueueProp.getName());
        for (CardView card:qv.getCards()){
            String destination=card.getCar().getDestination();
            QueueProperties qp=null;
            //get queue params
            for (QueueProperties p:queuePropList){
                if (destination.equals(p.getName())){
                    qp=p;
                }
            }
            //check queue lenght
            if (queueFacade.getQueueCardCount(qp.getId())<qp.getSize()){
                queueFacade.moveCard(card, qv, queueFacade.getQueue(qp.getId()));
                //inform 
                QueueView infoQueue = queueFacade.getQueue(InfoQueueProp.getId());
                infoQueue.getCards().add(0,card);
                queueFacade.update(infoQueue);
            }
        }
    }
}
