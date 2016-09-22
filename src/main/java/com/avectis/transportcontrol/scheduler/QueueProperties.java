/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.scheduler;

import com.avectis.transportcontrol.entity.QueueType;

/**
 *
 * @author Dima
 */
public class QueueProperties {
    private String name;
    private int size;
    private QueueType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public QueueType getType() {
        return type;
    }

    public void setType(QueueType type) {
        this.type = type;
    }

    public QueueProperties() {
    }

    @Override
    public String toString() {
        return "QueueProperties{" + "name=" + name + ", size=" + size + ", type=" + type + '}';
    }

}
