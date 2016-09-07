/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.Car;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DPoplauski
 */
public interface CarDAO {
    public Long addCar(Car car);
    public void update(Car car);
    public Car getCar(Long id);
    public List<Car> getCars(Date startDate, Date endDate);
    public void deleteCar(Car car);
}
