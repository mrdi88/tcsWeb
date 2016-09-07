/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
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
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment",strategy="increment")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "cargoId")
    private Cargo cargo;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "driverId")
    private Driver driver;
    private String destination;
    private String firstNumber;
    private String secondNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;

    public Date getLeaveDate() {
        return leaveDate;
    }
    public void setLeaveDate(Date leaveDate) {
        if(leaveDate!=null){
            leaveDate.setTime(leaveDate.getTime()-leaveDate.getTime()%1000);
        }
        
        this.leaveDate = leaveDate;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        if(createDate!=null){
            createDate.setTime(createDate.getTime()-createDate.getTime()%1000);
        }
        this.createDate = createDate;
    }
    public String getSecondNumber() {
        return secondNumber;
    }
    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }
    public String getFirstNumber() {
        return firstNumber;
    }
    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public Cargo getCargo() {
        return cargo;
    }
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    public Long getId() {
        return id;
    }
    public void setId(long carId) {
        this.id = carId;
    }
    public Car() {
        
    }

    public Car(Cargo cargo, Driver driver, String firstNumber, String secondNumber, String destination) {
        this.cargo = cargo;
        this.driver = driver;
        this.destination = destination;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        Date dt=new Date();
        dt.setTime(dt.getTime()-dt.getTime()%1000);
        this.createDate = dt;
    }

    @Override
    public String toString() {
        return "Car{" + "carId=" + id + ", cargo=" + cargo + ", driver=" + driver + ", destination=" + destination + ", firstNumber=" + firstNumber + ", secondNumber=" + secondNumber + ", createDate=" + createDate + ", leaveDate=" + leaveDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.cargo);
        hash = 67 * hash + Objects.hashCode(this.driver);
        hash = 67 * hash + Objects.hashCode(this.destination);
        hash = 67 * hash + Objects.hashCode(this.firstNumber);
        hash = 67 * hash + Objects.hashCode(this.secondNumber);
        hash = 67 * hash + Objects.hashCode(this.createDate);
        hash = 67 * hash + Objects.hashCode(this.leaveDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Car)) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (this.getCargo()!=null){
            if (!this.getCargo().equals(other.getCargo())) {
                return false;
            }
        } else{
            if (other.getCargo()!=null) return false;
        }
        if (this.getDriver()!=null){
            if (!this.getDriver().equals(other.getDriver())) {
                return false;
            }
        } else{
            if (other.getDriver()!=null) return false;
        }
        if (!Objects.equals(this.getDestination(), other.getDestination())) {
            return false;
        }
        if (!Objects.equals(this.getFirstNumber(), other.getFirstNumber())) {
            return false;
        }
        if (!Objects.equals(this.getSecondNumber(), other.getSecondNumber())) {
            return false;
        }
        if ((!Objects.equals(this.getCreateDate(), other.getCreateDate()))) {
                return false;
        }
        if ((!Objects.equals(this.getLeaveDate(), other.getLeaveDate()))) {
                return false;
        }
        return true;
    }
}
