/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.Car;
import com.avectis.transportcontrol.entity.Cargo;
import com.avectis.transportcontrol.entity.Driver;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 *
 * @author Dima
 */
public class CarView {

    private long id;
    private CargoView cargo;
    private DriverView driver;
    private String siloNumber;
    private String destination;
    private String carNumber;
    private String ttnNumber;
    private String nomenclature;
    private Date createDate;
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
    public DriverView getDriver() {
        return driver;
    }
    public void setDriver(DriverView driver) {
        this.driver = driver;
    }
    public CargoView getCargo() {
        return cargo;
    }
    public void setCargo(CargoView cargo) {
        this.cargo = cargo;
    }
    public Long getId() {
        return id;
    }
    public void setId(long carId) {
        this.id = carId;
    }
    public CarView() {
        
    }
    public CarView(Car car) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Cargo cargoEntity=car.getCargo();
        Driver driverEntity=car.getDriver();
        if (cargoEntity!=null) this.cargo=new CargoView(cargoEntity);
        if (driverEntity!=null) this.driver=new DriverView(driverEntity);
        if (car.getCreateDate()!=null){
            this.createDate=new Date(car.getCreateDate().getTime());
        }else{
            this.createDate=null;
        }
        if (car.getLeaveDate()!=null){
            this.leaveDate=new Date(car.getLeaveDate().getTime());
        }else{
            this.leaveDate=null;
        }
        this.siloNumber=car.getSiloNumber();
        this.destination=car.getDestination();
        this.carNumber=car.getCarNumber();
        this.id=car.getId();
        this.ttnNumber=car.getTtnNumber();
        this.nomenclature=car.getNomenclature();
    }

    @Override
    public String toString() {
        return "CarView{" + "id=" + id + ", cargo=" + cargo + ", driver=" + driver + ", siloNumber=" + siloNumber + ", destination=" + destination + ", carNumber=" + carNumber + ", ttnNumber=" + ttnNumber + ", nomenclature=" + nomenclature + ", createDate=" + createDate + ", leaveDate=" + leaveDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.cargo);
        hash = 67 * hash + Objects.hashCode(this.driver);
        hash = 67 * hash + Objects.hashCode(this.siloNumber);
        hash = 67 * hash + Objects.hashCode(this.destination);
        hash = 67 * hash + Objects.hashCode(this.carNumber);
        hash = 67 * hash + Objects.hashCode(this.ttnNumber);
        hash = 67 * hash + Objects.hashCode(this.nomenclature);
        hash = 67 * hash + Objects.hashCode(this.createDate);
        hash = 67 * hash + Objects.hashCode(this.leaveDate);
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
        final CarView other = (CarView) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        if (!Objects.equals(this.driver, other.driver)) {
            return false;
        }
        if (!Objects.equals(this.siloNumber, other.siloNumber)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.carNumber, other.carNumber)) {
            return false;
        }
        if (!Objects.equals(this.ttnNumber, other.ttnNumber)) {
            return false;
        }
        if (!Objects.equals(this.nomenclature, other.nomenclature)) {
            return false;
        }
        if ((!Objects.equals(this.createDate, other.createDate))) {
                return false;
        }
        if ((!Objects.equals(this.leaveDate, other.leaveDate))) {
                return false;
        }
        return true;
    }
}

