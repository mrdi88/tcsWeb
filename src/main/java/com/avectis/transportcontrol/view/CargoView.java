/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Cargo;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 *
 * @author Dima
 */
public class CargoView {

    private long id;
    private SampleView sample;
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

    public SampleView getSample() {
        return sample;
    }

    public void setSample(SampleView sample) {
        this.sample = sample;
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
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        if (cargo.getDischargeDate()!=null){
            this.dischargeDate=new Date(cargo.getDischargeDate().getTime());
        }else{
            this.dischargeDate=null;
        }
        if (cargo.getLoadingDate()!=null){
            this.loadingDate=new Date(cargo.getLoadingDate().getTime());
        }else{
            this.loadingDate=null;
        }
        this.dischargingPlace=cargo.getDischargingPlace();
        this.id=cargo.getId();
        this.loadingPlace=cargo.getLoadingPlace();
        if (cargo.getSample()!=null){
            this.sample=new SampleView(cargo.getSample());
        } else{
            this.sample=null;
        }
        this.weightIn=cargo.getWeightIn();
        this.weightOut=cargo.getWeightOut();
    }

    @Override
    public String toString() {
        return "CargoView{" + "id=" + id + ", sample=" + sample + ", weightIn=" + weightIn + ", weightOut=" + weightOut + ", dischargingPlace=" + dischargingPlace + ", dischargeDate=" + dischargeDate + ", loadingPlace=" + loadingPlace + ", loadingDate=" + loadingDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.getSample());
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
        if (this == obj) {
            return true;
        }
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
        if (this.weightIn != other.weightIn) {
            return false;
        }
        if (this.weightOut != other.weightOut) {
            return false;
        }
        if (!Objects.equals(this.getSample(), other.getSample())) {
            return false;
        }
        if (!Objects.equals(this.dischargingPlace, other.dischargingPlace)) {
            return false;
        }
        if (!Objects.equals(this.loadingPlace, other.loadingPlace)) {
            return false;
        }
        if (!Objects.equals(this.dischargeDate, other.dischargeDate)) {
            return false;
        }
        if (!Objects.equals(this.loadingDate, other.loadingDate)) {
            return false;
        }
        return true;
    }

}
