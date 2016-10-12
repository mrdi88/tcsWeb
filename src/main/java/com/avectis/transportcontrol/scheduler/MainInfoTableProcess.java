/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.scheduler;

import com.avectis.transportcontrol.control.infotable.InfoTable;
import com.avectis.transportcontrol.exception.ConnectionFailException;
import com.avectis.transportcontrol.facade.InfoTableFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.QueueView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dima
 */
public class MainInfoTableProcess {
    private QueueFacade queueFacade;
    private InfoTableFacade infoTableFacade;
    private String infoTableName;
    private String infoQueueName;
    //for change display
    private int rowCount=5; //row count of table
    private int displayStep;
    private List<CardView> currentDisplayList=new ArrayList();

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    public void setInfoTableFacade(InfoTableFacade infoTableFacade) {
        this.infoTableFacade = infoTableFacade;
    }

    public void setInfoTableName(String infoTableName) {
        this.infoTableName = infoTableName;
    }

    public void setInfoQueueName(String infoQueueName) {
        this.infoQueueName = infoQueueName;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    public MainInfoTableProcess() {
    }
    
    public void doJob() throws ConnectionFailException{
        //update currentDisplayList
        if (displayStep<0){ 
            currentDisplayList.clear();
            //move from buffer to queue if the length is less than max
            QueueView qv = queueFacade.getQueueByName(infoQueueName);
            int i=0;
            for (CardView card:qv.getCards()){
                if (i<rowCount){
                    currentDisplayList.add(card);
                }else{
                    break;
                }
                i++;
            }
            if (i>0){
                displayStep=0;
            }else{
                //if no data to display
                InfoTable infoTable=infoTableFacade.GetElementById(infoTableName);
                List<String> rows=new ArrayList();
                rows.add(" ");
                infoTable.SendData(rows);
                displayStep=-1;
            }
            return;
        }
        
        InfoTable infoTable=infoTableFacade.GetElementById(infoTableName);
        List<String> rows=new ArrayList();
        //show all car number and directions
        if (displayStep==0){
            for (CardView card:currentDisplayList){
                StringBuilder row=new StringBuilder();
                row.append(card.getCar().getCarNumber());
                row.append(" ");
                row.append(card.getCar().getDestination());
                rows.add(row.toString());
            }
            infoTable.SendData(rows);
            displayStep++;
            return;
        }
        
        //show each car description
        int i=0;
        CardView displayCard=null;
        for (CardView card:currentDisplayList){
            i++;
            if (i>=displayStep){ //card to display
                displayCard=card;
                break;
            }
        }
        if (displayCard!=null){
            rows.add(displayCard.getCar().getCarNumber()); //car number
            rows.add(displayCard.getCar().getCarNumber());//nomenclature
            rows.add("влажн  "+displayCard.getCar().getCarNumber()+"%");//humidity
            rows.add("класс  "+displayCard.getCar().getCarNumber());//class
            rows.add("путь   "+displayCard.getCar().getCarNumber());//discharge point
        }else{
            rows.add(" ");
        }
        infoTable.SendData(rows);
        //check if all cards was displaide
        if (displayStep>=currentDisplayList.size()){
            displayStep=-1;
        }else{
            displayStep++;
        }
    }
}
