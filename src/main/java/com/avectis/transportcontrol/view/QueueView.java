/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Card;
import com.avectis.transportcontrol.entity.Queue;
import com.avectis.transportcontrol.entity.QueueType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dima
 */
public class QueueView {

    private long id;
    private String name;
    private QueueType type;
    private List<CardView> cards=new ArrayList<>();

    public QueueType getType() {
        return type;
    }

    public void setType(QueueType type) {
        this.type = type;
    }
    
    public List<CardView> getCards() {
        return cards;
    }
    public void setCards(List<CardView> cards) {
        this.cards = cards;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public QueueView() {
    }

    public QueueView(Queue queue) {
        this.id=queue.getId();
        this.name = queue.getName();
        this.type = queue.getType();
        List<CardView> cardsV=new ArrayList<>();
        if (queue.getCards()!=null){
            for (Card card:queue.getCards()){
                cardsV.add(new CardView(card));
            }
        }
        this.cards = cardsV;
        
    }

    @Override
    public String toString() {
        return "QueueView{" + "id=" + id + ", name=" + name + ", type=" + type + ", cards=" + cards + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.cards);
        hash = 83 * hash + Objects.hashCode(this.type);
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
        final QueueView other = (QueueView) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getCards(), other.getCards())) {
            return false;
        }
        if (!Objects.equals(this.getType(), other.getType())) {
            return false;
        }
        return true;
    }
    
}

