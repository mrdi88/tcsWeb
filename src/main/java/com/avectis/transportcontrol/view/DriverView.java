/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Driver;
import java.util.Objects;

/**
 *
 * @author Dima
 */
public class DriverView {
    
    private long id;
    private String name;
    private String mobileNumber;
    private String organization;

    public DriverView(Driver driver) {
        this.id=driver.getId();
        this.mobileNumber=driver.getMobileNumber();
        this.name=driver.getName();
        this.organization=driver.getOrganization();
    }
    public DriverView(String name, String mobileNumber, String organization) {
        this.name=name;
        this.mobileNumber=mobileNumber;
        this.organization=organization;
    }
    public DriverView() {
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
    public long getId() {
        return id;
    }
    public void setId(long driverId) {
        this.id = driverId;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DriverView other = (DriverView) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mobileNumber, other.mobileNumber)) {
            return false;
        }
        if (!Objects.equals(this.organization, other.organization)) {
            return false;
        }
        return true;
    }
    
}
