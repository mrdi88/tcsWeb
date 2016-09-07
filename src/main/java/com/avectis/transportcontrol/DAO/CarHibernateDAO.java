/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.Car;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DPoplauski
 */
public class CarHibernateDAO extends BaseHibernateDAO implements CarDAO{
     /**
     * add new Car entity using Hibernate
     * 
     * @param car Car
     * @return created Car entity
     */
    @Override
    public Long addCar(Car car){
        Session session=sessionFactory.getCurrentSession();
        return (Long)session.save(car);
    }
     /**
     * unpade entity using Hibernate
     * 
     * @param car Car - witch entity to update
     */
    @Override
    public void update(Car car){
        Session session=sessionFactory.getCurrentSession();
        session.update(car);
    }
    /**
     * get Car object from DB using Hibernate
     * 
     * @param id
     * @return Car object
     */
    @Override
    public Car getCar(Long id){
        Session session=sessionFactory.getCurrentSession();
        return (Car) session.load(Car.class, id);
    }
    /**
     * get Cars for period from DB using Hibernate
     * 
     * @param startDate Date - from date
     * @param endDate Date - to date
     * @return List of Car objects
     */
    @Override
    public List<Car> getCars(Date startDate, Date endDate){
        Session session=sessionFactory.getCurrentSession();
        Criteria criteria=session.createCriteria(Car.class);
        if(startDate != null) {
            criteria.add(Restrictions.ge("createDate", startDate));
        }
        if(endDate != null) {
            criteria.add(Restrictions.le("createDate", endDate));
        }
        criteria.addOrder(Order.asc("createDate"));
        return (List<Car>)criteria.list();
    }
    /**
     * delete Car object from DB using Hibernate
     * 
     * @param car Car - car object to delete
     */
    @Override
    public void deleteCar(Car car){
        Session session=sessionFactory.getCurrentSession();
        session.delete(car);
    }
}
