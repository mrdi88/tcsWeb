/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author DPoplauski
 */
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    private long id;
    private String name;
    private String mobileNumber;
    private String organization;

    public Driver(String name, String mobileNumber, String organization) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.organization = organization;
    }
    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
    public void setId(long driverId) {
        this.id = driverId;
    }
    public Driver() {
    }

    @Override
    public String toString() {
        return "Driver{" + "driverId=" + id + ", name=" + name + ", mobileNumber=" + mobileNumber + ", organization=" + organization + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.mobileNumber);
        hash = 71 * hash + Objects.hashCode(this.organization);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Driver)) {
            return false;
        }
        final Driver other = (Driver) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.getMobileNumber(), other.getMobileNumber())) {
            return false;
        }
        if (!Objects.equals(this.getOrganization(), other.getOrganization())) {
            return false;
        }
        return true;
    }
    
}
