/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.entity;

import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
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
    private String siloNumber;
    private String destination;
    private String carNumber;
    private String ttnNumber;
    private String nomenclature;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getSiloNumber() {
        return siloNumber;
    }

    public void setSiloNumber(String siloNumber) {
        this.siloNumber = siloNumber;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }
    public void setLeaveDate(Date leaveDate) {
        if(leaveDate!=null){
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            leaveDate.setTime(leaveDate.getTime()-leaveDate.getTime()%1000);
        }
        
        this.leaveDate = leaveDate;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        if(createDate!=null){
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            createDate.setTime(createDate.getTime()-createDate.getTime()%1000);
        }
        this.createDate = createDate;
    }
    public String getTtnNumber() {
        return ttnNumber;
    }
    public void setTtnNumber(String secondNumber) {
        this.ttnNumber = secondNumber;
    }
    public String getCarNumber() {
        return carNumber;
    }
    public void setCarNumber(String firstNumber) {
        this.carNumber = firstNumber;
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

    public Car(Cargo cargo, Driver driver, String firstNumber, String secondNumber, String siloNumber, String destination, String nomenclature) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        this.cargo = cargo;
        this.driver = driver;
        this.siloNumber=siloNumber;
        this.destination = destination;
        this.carNumber = firstNumber;
        this.ttnNumber = secondNumber;
        this.nomenclature=nomenclature;
        Date dt=new Date();
        dt.setTime(dt.getTime()-dt.getTime()%1000);
        this.createDate = dt;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", cargo=" + cargo + ", driver=" + driver + ", siloNumber=" + siloNumber + ", destination=" + destination + ", carNumber=" + carNumber + ", ttnNumber=" + ttnNumber + ", nomenclature=" + nomenclature + ", createDate=" + createDate + ", leaveDate=" + leaveDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 67 * hash + Objects.hashCode(this.getCargo());
        hash = 67 * hash + Objects.hashCode(this.getDriver());
        hash = 67 * hash + Objects.hashCode(this.getSiloNumber());
        hash = 67 * hash + Objects.hashCode(this.getDestination());
        hash = 67 * hash + Objects.hashCode(this.getCarNumber());
        hash = 67 * hash + Objects.hashCode(this.getTtnNumber());
        hash = 67 * hash + Objects.hashCode(this.getNomenclature());
        hash = 67 * hash + Objects.hashCode(this.getCreateDate());
        hash = 67 * hash + Objects.hashCode(this.getLeaveDate());
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
        if (!Objects.equals(this.getNomenclature(), other.getNomenclature())) {
            return false;
        }
        if (!Objects.equals(this.getSiloNumber(), other.getSiloNumber())) {
            return false;
        }
        if (!Objects.equals(this.getDestination(), other.getDestination())) {
            return false;
        }
        if (!Objects.equals(this.getCarNumber(), other.getCarNumber())) {
            return false;
        }
        if (!Objects.equals(this.getTtnNumber(), other.getTtnNumber())) {
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
