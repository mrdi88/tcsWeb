/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Queue;
import com.avectis.transportcontrol.entity.QueueType;
import java.util.Objects;

/**
 *
 * @author Dima
 */
public class QueueNameView {

    private long id;
    private String name;
    private QueueType type;

    public QueueType getType() {
        return type;
    }

    public void setType(QueueType type) {
        this.type = type;
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
    public QueueNameView() {
    }

    public QueueNameView(Queue queue) {
        this.id=queue.getId();
        this.name = queue.getName();
    }

    @Override
    public String toString() {
        return "QueueNameView{" + "id=" + id + ", name=" + name + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.type);
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
        final QueueNameView other = (QueueNameView) obj;
        if (!Objects.equals(this.getType(), other.getType())) {
            return false;
        }
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        return true;
    }
    
}

