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
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String organization;

    public Driver(String firstName,String lastName, String mobileNumber, String organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.organization = organization;
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
        return "Driver{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber + ", organization=" + organization + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 71 * hash + Objects.hashCode(this.getFirstName());
        hash = 71 * hash + Objects.hashCode(this.getLastName());
        hash = 71 * hash + Objects.hashCode(this.getMobileNumber());
        hash = 71 * hash + Objects.hashCode(this.getOrganization());
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
        if (!Objects.equals(this.getFirstName(), other.getFirstName())) {
            return false;
        }
        if (!Objects.equals(this.getLastName(), other.getLastName())) {
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
