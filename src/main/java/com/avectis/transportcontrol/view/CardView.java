/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Card;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 *
 * @author Dima
 */
public class CardView {

    private long id;
    private CarView car;
    private String cardNumber;
    private int state;
    private int accessLevel;
    private Date createDate;
    
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        if (createDate!=null){
            createDate.setTime(createDate.getTime()-createDate.getTime()%1000);
        }
        this.createDate = createDate;
    }
    public int getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public CarView getCar() {
        return car;
    }
    public void setCar(CarView car) {
        this.car = car;
    }
    public Long getId() {
        return id;
    }
    public void setId(long cardId) {
        this.id = cardId;
    }
    public CardView() {
        
    }
    public CardView(Card card) {
        if (card.getCar()!=null){
            this.car= new CarView(card.getCar());
        }
        else this.car=null;
        this.accessLevel=card.getAccessLevel();
        this.cardNumber=card.getCardNumber();
        if (card.getCreateDate()!=null){
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            this.createDate=new Date(card.getCreateDate().getTime());
        }else{
            this.createDate=null;
        }
        
        this.id=card.getId();
        this.state=card.getState();
    }

    @Override
    public String toString() {
        return "Card{" + "cardId=" + id + ", car=" + car + ", cardNumber=" + cardNumber + ", state=" + state + ", accessLevel=" + accessLevel + ", createDate=" + createDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CardView other = (CardView) obj;
        if (!Objects.equals(this.car, other.car)) {
            return false;
        }
        if (!Objects.equals(this.cardNumber, other.cardNumber)) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        if (this.accessLevel != other.accessLevel) {
            return false;
        }
        if (!Objects.equals(this.createDate, other.createDate)) {
                return false;
        }
        return true;
    }
    
}
