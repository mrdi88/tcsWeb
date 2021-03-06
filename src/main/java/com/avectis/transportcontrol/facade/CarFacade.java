/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.DAO.CarDAO;
import com.avectis.transportcontrol.entity.Car;
import com.avectis.transportcontrol.entity.Cargo;
import com.avectis.transportcontrol.entity.Driver;
import com.avectis.transportcontrol.entity.Sample;
import com.avectis.transportcontrol.view.CarView;
import com.avectis.transportcontrol.view.SampleView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Dima
 */
public class CarFacade {

    public CarFacade() {
    }
    
    private CarDAO carDAO;

    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    } 
    @Transactional
    public Long add(CarView car){
        return carDAO.addCar(carFromView(car));
    }
    @Transactional
    public void update(CarView car){
        Car c=carFromView(car);
        carDAO.update(c);
    }
    @Transactional(readOnly = true)
    public CarView get(Long id){
        Car car = carDAO.getCar(id);
        if (car!=null) return new CarView(car);
        else return null;
    }
    @Transactional(readOnly = true)
    public List<CarView> getList(Date from, Date to){
        List<Car> cars=carDAO.getCars(from, to);
        List<CarView> carsView= new ArrayList<>();
        for (Car car:cars){
            carsView.add(new CarView(car));
        }
        return carsView;
    }
    @Transactional(readOnly = true)
    public List<CarView> getList(Date from, Date to, String carNumber){
        List<Car> cars=carDAO.getCars(from, to, carNumber);
        List<CarView> carsView= new ArrayList<>();
        for (Car car:cars){
            carsView.add(new CarView(car));
        }
        return carsView;
    }
    @Transactional
    public void delete(CarView car){
        carDAO.deleteCar(carFromView(car));
    }
    @Transactional
    public void deleteList(List<CarView> cars){
        for (CarView car:cars){
            carDAO.deleteCar(carFromView(car));
        }
    }
    
    public Car carFromView(CarView carv){
        Car car;
        if (carv.getId() != null && carv.getId() > 0) {
            car = carDAO.getCar(carv.getId());
        } else {
            car = new Car();
        }
        car.setCreateDate(carv.getCreateDate());
        car.setSiloNumber(carv.getSiloNumber());
        car.setDestination(carv.getDestination());
        car.setCarNumber(carv.getCarNumber());
        car.setLeaveDate(carv.getLeaveDate());
        car.setTtnNumber(carv.getTtnNumber());
        car.setNomenclature(carv.getNomenclature());
        //create Cargo
        Cargo cargo;
        if (car.getCargo()!=null){
            cargo = car.getCargo();
        }else{
            cargo = new Cargo();
        }
        if (carv.getCargo()!=null){
            cargo.setDischargeDate(carv.getCargo().getDischargeDate());
            cargo.setDischargingPlace(carv.getCargo().getDischargingPlace());
            cargo.setLoadingDate(carv.getCargo().getLoadingDate());
            cargo.setLoadingPlace(carv.getCargo().getLoadingPlace());
            cargo.setSample(sampleFromView(carv.getCargo().getSample()));
            cargo.setWeightIn(carv.getCargo().getWeightIn());
            cargo.setWeightOut(carv.getCargo().getWeightOut());
        }
        car.setCargo(cargo);
        //create Driver
        Driver driver;
        if (car.getDriver()!=null){
            driver = car.getDriver();
        }else{
            driver = new Driver();
        }
        if (carv.getDriver()!=null){
            driver.setMobileNumber(carv.getDriver().getMobileNumber());
            driver.setFirstName(carv.getDriver().getFirstName());
            driver.setLastName(carv.getDriver().getLastName());
            driver.setOrganization(carv.getDriver().getOrganization());
        }
        car.setDriver(driver);

        return car;
    }
    private Sample sampleFromView(SampleView samplev){
        Sample sample=null;
        if (samplev!=null){
            sample=new Sample();
            sample.setId(samplev.getId());
            sample.setName(samplev.getName());
            sample.setWeediness(samplev.getWeediness());
            sample.setGluten(samplev.getGluten());
            sample.setHumidity(samplev.getHumidity());
            sample.setCultureClass(samplev.getCultureClass());
            sample.setNomenclature(samplev.getNomenclature());
        }
        return sample;
    }
}

