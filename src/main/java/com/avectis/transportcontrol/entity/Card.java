/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.entity;

import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author DPoplauski
 */
@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment",strategy="increment")
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId")
    private Car car;
    private String cardNumber;
    private int state;
    private int accessLevel;
    @Temporal(TemporalType.TIMESTAMP)
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
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public Long getId() {
        return id;
    }
    public void setId(long cardId) {
        this.id = cardId;
    }
    public Card() {
        
    }

    public Card(Car car, String cardNumber, int state, int accessLevel) {
        this.car = car;
        this.cardNumber = cardNumber;
        this.state = state;
        this.accessLevel = accessLevel;
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Date dt=new Date();
        dt.setTime(dt.getTime()-dt.getTime()%1000);
        this.createDate=dt;
    }

    @Override
    public String toString() {
        return "Card{" + "cardId=" + id + ", car=" + car + ", cardNumber=" + cardNumber + ", state=" + state + ", accessLevel=" + accessLevel + ", createDate=" + createDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 13 * hash + Objects.hashCode(this.getCar());
        hash = 13 * hash + Objects.hashCode(this.getCardNumber());
        hash = 13 * hash + this.getState();
        hash = 13 * hash + this.getAccessLevel();
        hash = 13 * hash + Objects.hashCode(this.getCreateDate());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Card)) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.getCar()!=null){
            if (!this.getCar().equals(other.getCar())) {
                return false;
            }
        } else{
            if (other.getCar()!=null) return false;
        }
        if (!Objects.equals(this.getCardNumber(),other.getCardNumber())) {
            return false;
        }
        if (this.getState() != other.getState()) {
            return false;
        }
        if (this.getAccessLevel() != other.getAccessLevel()) {
            return false;
        }
        if (!Objects.equals(this.getCreateDate(), other.getCreateDate())) {
                return false;
        }
        return true;
    }
    
}
