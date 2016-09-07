/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Cargo;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Dima
 */
public class CargoView {

    private long id;
    private int quality;
    private int weightIn;
    private int weightOut;
    private String dischargingPlace;
    private Date dischargeDate;
    private String loadingPlace;
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
    public int getQuality() {
        return quality;
    }
    public void setQuality(int quality) {
        this.quality = quality;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public CargoView() {
    }
    

    public CargoView(Cargo cargo) {
        this.dischargeDate=cargo.getDischargeDate();
        this.dischargingPlace=cargo.getLoadingPlace();
        this.id=cargo.getId();
        this.loadingDate=cargo.getLoadingDate();
        this.loadingPlace=cargo.getLoadingPlace();
        this.quality=cargo.getQuality();
        this.weightIn=cargo.getWeightIn();
        this.weightOut=cargo.getWeightOut();
    }

    @Override
    public String toString() {
        return "Cargo{" + "cargoId=" + id + ", quality=" + quality + ", weightIn=" + weightIn + ", weightOut=" + weightOut + ", dischargingPlace=" + dischargingPlace + ", dischargeDate=" + dischargeDate + ", loadingPlace=" + loadingPlace + ", loadingDate=" + loadingDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + this.quality;
        hash = 37 * hash + this.weightIn;
        hash = 37 * hash + this.weightOut;
        hash = 37 * hash + Objects.hashCode(this.dischargingPlace);
        hash = 37 * hash + Objects.hashCode(this.dischargeDate);
        hash = 37 * hash + Objects.hashCode(this.loadingPlace);
        hash = 37 * hash + Objects.hashCode(this.loadingDate);
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
        final CargoView other = (CargoView) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quality != other.quality) {
            return false;
        }
        if (this.weightIn != other.weightIn) {
            return false;
        }
        if (this.weightOut != other.weightOut) {
            return false;
        }
        if (!Objects.equals(this.dischargingPlace, other.dischargingPlace)) {
            return false;
        }
        return true;
    }
}
