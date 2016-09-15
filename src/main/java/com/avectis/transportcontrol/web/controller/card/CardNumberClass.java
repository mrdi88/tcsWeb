/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.web.controller.card;

/**
 *
 * @author Dima
 */
public class CardNumberClass {
    String cardNumber;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardNumberClass(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
}
