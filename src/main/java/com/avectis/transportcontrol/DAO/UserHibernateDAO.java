/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.User;
import com.avectis.transportcontrol.entity.UserRole;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author DPoplauski
 */
public class UserHibernateDAO extends BaseHibernateDAO implements UserDAO{

    @Override
    public void addUser(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public Long addUserRole(UserRole userRole) {
        Session session=sessionFactory.getCurrentSession();
        return (Long)session.save(userRole);
    }

    @Override
    public void update(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void update(UserRole userRole) {
        Session session=sessionFactory.getCurrentSession();
        session.update(userRole);
    }

    @Override
    public User getUserByName(String username) {
        Session session=sessionFactory.getCurrentSession();
        List<User> users = new ArrayList<User>();
        users = session
                .createQuery("from User where username=?")
                .setParameter(0, username).list();
        if (users.size() > 0) {
                return users.get(0);
        } else {
                return null;
        }
    }

    @Override
    public UserRole getUserRole(Long id) {
        Session session=sessionFactory.getCurrentSession();
        return (UserRole) session.load(UserRole.class, id);
    }

    @Override
    public List<User> getUsers() {
        Session session=sessionFactory.getCurrentSession();
        return session.createCriteria(User.class).list();
    }

    @Override
    public List<UserRole> getUserRoles() {
        Session session=sessionFactory.getCurrentSession();
        return session.createCriteria(UserRole.class).list();
    }

    @Override
    public void deleteUser(User user) {
        Session session=sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void deleteUserRole(UserRole userRole) {
        Session session=sessionFactory.getCurrentSession();
        session.delete(userRole);
    }
    
}
