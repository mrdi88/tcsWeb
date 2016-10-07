/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.entity;

import com.avectis.transportcontrol.util.QueueType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author DPoplauski
 */
@Entity
@Table(name="queues")
public class Queue {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment",strategy="increment")
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name="type")  
    private QueueType type;
    @OneToMany(fetch = FetchType.LAZY)
    @OrderColumn(name="order_id")
    private List<Card> cards=new ArrayList<>();

    public QueueType getType() {
        return type;
    }

    public void setType(QueueType type) {
        this.type = type;
    }
    
    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List cards) {
        this.cards = cards;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Queue() {
    }

    public Queue(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Queue{" + "id=" + id + ", name=" + name + ", type=" + type + ", cards=" + cards + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 17 * hash + Objects.hashCode(this.getName());
        hash = 17 * hash + Objects.hashCode(this.getCards());
        hash = 17 * hash + Objects.hashCode(this.getType());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Queue)) {
            return false;
        }
        final Queue other = (Queue) obj;
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
