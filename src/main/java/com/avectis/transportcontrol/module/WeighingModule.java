/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.module;

import com.avectis.transportcontrol.control.scales.Scales;
import com.avectis.transportcontrol.control.scanner.CardScanner;
import com.avectis.transportcontrol.control.scanner.CardScannerListener;
import com.avectis.transportcontrol.exception.ConnectionFailException;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.view.CardView;

/**
 *
 * @author Dima
 */
public class WeighingModule {
    
    private CardFacade cardFacade;
    
    private Scales weightInScales;
    private Scales weightOutScales;
    private CardScanner weightInScanner;
    private CardScanner weightOutScanner;

    public WeighingModule() {
    }

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setWeightInScales(Scales weightInScales) {
        this.weightInScales = weightInScales;
    }

    public void setWeightOutScales(Scales weightOutScales) {
        this.weightOutScales = weightOutScales;
    }

    public void setWeightInScanner(CardScanner weightInScanner) {
        this.weightInScanner = weightInScanner;
    }

    public void setWeightOutScanner(CardScanner weightOutScanner) {
        this.weightOutScanner = weightOutScanner;
    }

    
    public void init(){
        //weight In
        CardScannerListener cardListener=new CardScannerListener(){
            @Override 
            public void onCardLogined(String CardNumberHEX,String CardNumberDEC){
                try {
                    CardView card=cardFacade.getCardByNumber(CardNumberDEC);
                    int weight=weightInScales.getWeight();
                    card.getCar().getCargo().setWeightIn(weight);
                    cardFacade.update(card);
                } catch (ConnectionFailException ex) {
                    ex.printStackTrace();
                    //Logger.getLogger(WeighingModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        weightInScanner.addListener(cardListener);
        //weight Out
        cardListener=new CardScannerListener(){
            @Override 
            public void onCardLogined(String CardNumberHEX,String CardNumberDEC){
                try {
                    CardView card=cardFacade.getCardByNumber(CardNumberDEC);
                    int weight=weightOutScales.getWeight();
                    card.getCar().getCargo().setWeightOut(weight);
                    cardFacade.update(card);
                } catch (ConnectionFailException ex) {
                    ex.printStackTrace();
                    //Logger.getLogger(WeighingModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        weightOutScanner.addListener(cardListener);
    }
}
