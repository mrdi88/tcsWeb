/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.module;

import com.avectis.transportcontrol.control.barrier.Barrier;
import com.avectis.transportcontrol.control.relay.Relay;
import com.avectis.transportcontrol.control.scanner.CardScanner;
import com.avectis.transportcontrol.control.scanner.CardScannerListener;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.web.controller.card.CardController;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Dima
 */
public class CardReciverModule {
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CardController.class.getName());
    
    private CardFacade cardFacade;
    private QueueFacade queueFacade;
    private Barrier exitBarrier;
    private Relay acceptRelay;
    private CardScanner cardReciverScanner;

    public CardReciverModule() {
    }

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setExitBarrier(Barrier exitBarrier) {
        this.exitBarrier = exitBarrier;
    }

    public void setAcceptRelay(Relay acceptRelay) {
        this.acceptRelay = acceptRelay;
    }

    public void setCardReciverScanner(CardScanner cardReciverScanner) {
        this.cardReciverScanner = cardReciverScanner;
    }

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }
    
    
    public void init(){
        //weight In
        CardScannerListener cardListener=new CardScannerListener(){
            @Override 
            public void onCardLogined(String CardNumberHEX,String CardNumberDEC){
                try {
                    CardView card=cardFacade.getCardByNumber(CardNumberDEC);
                    //если карта есть в системе убираем ее и даем добро на выезд
                    if (card!=null){
                        queueFacade.deleteCardFromQueues(card);
                        cardFacade.delete(card);
                        acceptRelay.pulseDO1();
                        exitBarrier.Open();
                    }
                } catch (Exception ex) {
                    logger.error("CardReciverModule: "+ex.getMessage());
                }
            }
        };
        cardReciverScanner.addListener(cardListener);

    }
}
