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

/**
 *
 * @author Dima
 */
public class CarView {

    private long id;
    private CargoView cargo;
    private DriverView driver;
    private String destination;
    private String firstNumber;
    private String secondNumber;
    private Date createDate;
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
        Cargo cargoEntity=car.getCargo();
        Driver driverEntity=car.getDriver();
        if (cargoEntity!=null) this.cargo=new CargoView(cargoEntity);
        if (driverEntity!=null) this.driver=new DriverView(driverEntity);
        this.createDate=car.getCreateDate();
        this.destination=car.getDestination();
        this.firstNumber=car.getFirstNumber();
        this.id=car.getId();
        this.leaveDate=car.getLeaveDate();
        this.secondNumber=car.getSecondNumber();
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
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.firstNumber, other.firstNumber)) {
            return false;
        }
        if (!Objects.equals(this.secondNumber, other.secondNumber)) {
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

