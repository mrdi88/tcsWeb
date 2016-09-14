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
@Table(name="cargos")
public class Cargo {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment",strategy="increment")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "sampleId")
    private Sample sample;
    private int weightIn;
    private int weightOut;
    private String dischargingPlace;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dischargeDate;
    private String loadingPlace;
    @Temporal(TemporalType.TIMESTAMP)
    private Date loadingDate;

    public Date getLoadingDate() {
        return loadingDate;
    }
    public void setLoadingDate(Date loadingDate) {
        if (loadingDate!=null){
            loadingDate.setTime(loadingDate.getTime()-loadingDate.getTime()%1000);
        }
        this.loadingDate = loadingDate;
    }
    public String getLoadingPlace() {
        return loadingPlace;
    }
    public void setLoadingPlace(String loadingPlace) {
        this.loadingPlace = loadingPlace;
    }
    public Date getDischargeDate() {
        return dischargeDate;
    }
    public void setDischargeDate(Date dischargeDate) {
        if (dischargeDate!=null){
            dischargeDate.setTime(dischargeDate.getTime()-dischargeDate.getTime()%1000);
        }
        this.dischargeDate = dischargeDate;
    }
    public String getDischargingPlace() {
        return dischargingPlace;
    }
    public void setDischargingPlace(String dischargingPlace) {
        this.dischargingPlace = dischargingPlace;
    }
    public int getWeightOut() {
        return weightOut;
    }
    public void setWeightOut(int weightOut) {
        this.weightOut = weightOut;
    }
    public int getWeightIn() {
        return weightIn;
    }
    public void setWeightIn(int weightIn) {
        this.weightIn = weightIn;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public Cargo() {
    }

    @Override
    public String toString() {
        return "Cargo{" + "id=" + id + ", sample=" + this.getSample() + ", weightIn=" + weightIn + ", weightOut=" + weightOut + ", dischargingPlace=" + dischargingPlace + ", dischargeDate=" + dischargeDate + ", loadingPlace=" + loadingPlace + ", loadingDate=" + loadingDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.getId() ^ (this.getId() >>> 32));
        hash = 37 * hash + Objects.hashCode(this.getSample());
        hash = 37 * hash + this.getWeightIn();
        hash = 37 * hash + this.getWeightOut();
        hash = 37 * hash + Objects.hashCode(this.getDischargingPlace());
        hash = 37 * hash + Objects.hashCode(this.getDischargeDate());
        hash = 37 * hash + Objects.hashCode(this.getLoadingPlace());
        hash = 37 * hash + Objects.hashCode(this.getLoadingDate());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cargo)) {
            return false;
        }
        final Cargo other = (Cargo) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (this.getSample()!=null){
            if (!this.getSample().equals(other.getSample())) {
                return false;
            }
        } else{
            if (other.getSample()!=null) return false;
        }
        if (this.getWeightIn() != other.getWeightIn()) {
            return false;
        }
        if (this.getWeightOut() != other.getWeightOut()) {
            return false;
        }
        if (!Objects.equals(this.getDischargingPlace(), other.getDischargingPlace())) {
            return false;
        }
        if (!Objects.equals(this.getLoadingPlace(), other.getLoadingPlace())) {
            return false;
        }
        if (!Objects.equals(this.getDischargeDate(), other.getDischargeDate())) {
            return false;
        }
        if (!Objects.equals(this.getLoadingDate(), other.getLoadingDate())) {
            return false;
        }
        return true;
    }
    
}
