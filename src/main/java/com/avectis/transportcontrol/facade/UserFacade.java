/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.DAO.UserDAO;
import com.avectis.transportcontrol.entity.User;
import com.avectis.transportcontrol.entity.UserRole;
import com.avectis.transportcontrol.view.UserRoleView;
import com.avectis.transportcontrol.view.UserView;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Dima
 */
public class UserFacade {

    public UserFacade() {
    }
    
    private UserDAO userDAO;
    
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public User userFromView(UserView userV){
        User user;
        if (!userV.getUsername().isEmpty()) {
            user = userDAO.getUserByName(userV.getUsername());
        } else {
            user = new User();
        }
        user.setEnabled(userV.isEnabled());
        user.setUsername(userV.getUsername());
        user.setPassword(userV.getPassword());
        Set<UserRole> roleSet=new HashSet();
        for (UserRoleView rv:userV.getUserRole()){
            UserRole r =null;
            if (rv.getUserRoleId()!=null && rv.getUserRoleId()>0){
                r=userDAO.getUserRole(rv.getUserRoleId());
            }else{
                r=new UserRole();
            }
                r.    
            r.setRole(rv.getRole());
            r.setUserRoleId(rv.);
        }
        return user;
    }
    private UserRole userRoleFromView(UserRoleView userRoleV){
        UserRole userRole=null;
        if (userRoleV.getUserRoleId()!=null && userRoleV.getUserRoleId()>0){
            userRole=userDAO.getUserRole(userRoleV.getUserRoleId());
        }else{
            userRole=new UserRole();
        }
        userRole.setRole(userRoleV.getRole());
        userRole.setUser(userFromView(userRoleV.getUser()));
        userRole.setUserRoleId(userRoleV.getUserRoleId());
        return userRole;
    }
}

