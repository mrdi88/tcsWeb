/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;
import org.hibernate.SessionFactory;


/**
 *
 * @author Dima
 */
public abstract class BaseHibernateDAO {
    
    protected SessionFactory  sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
