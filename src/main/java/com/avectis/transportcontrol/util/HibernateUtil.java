/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author DPoplauski
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    // A SessionFactory is set up once for an application!
    
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                            .configure() // configures settings from hibernate.cfg.xml
                            .build();
            try {
                    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                    // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                    // so destroy it manually.
                    System.out.println("ex: " + e);
                    StandardServiceRegistryBuilder.destroy( registry );
            }
        }
        return sessionFactory;
    }
}
