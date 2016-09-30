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
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String organization;

    public DriverView(Driver driver) {
        this.id=driver.getId();
        this.mobileNumber=driver.getMobileNumber();
        this.firstName=driver.getFirstName();
        this.lastName=driver.getLastName();
        this.organization=driver.getOrganization();
    }
    public DriverView(String firstName, String lastName, String mobileNumber, String organization) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.mobileNumber=mobileNumber;
        this.organization=organization;
    }
    public DriverView() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public long getId() {
        return id;
    }
    public void setId(long driverId) {
        this.id = driverId;
    }

    @Override
    public String toString() {
        return "DriverView{" + "id=" + id + ", firstName=" + firstName + ", secondName=" + lastName + ", mobileNumber=" + mobileNumber + ", organization=" + organization + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 71 * hash + Objects.hashCode(this.lastName);
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
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
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
