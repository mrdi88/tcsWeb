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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void addUser(UserView user) {
        userDAO.addUser(userFromView(user));
    }

    @Transactional
    public Long addUserRole(UserRoleView userRole) {
        return userDAO.addUserRole(userRoleFromView(userRole));
    }

    @Transactional
    public void update(UserView userv) {
        User user=userFromView(userv);
        userDAO.update(user);
    }

    @Transactional
    public void update(UserRoleView userRole) {
        userDAO.update(userRoleFromView(userRole));
    }
    
    @Transactional(readOnly = true)
    public UserView getUserByName(String username) {
        return new UserView(userDAO.getUserByName(username));
    }

    @Transactional(readOnly = true)
    public UserRoleView getUserRole(Long id) {
        return new UserRoleView(userDAO.getUserRole(id));
    }

    @Transactional(readOnly = true)
    public List<UserView> getUsers() {
        List<User> userList=userDAO.getUsers();
        List<UserView> userViewList=new ArrayList();
        for (User user:userList){
            userViewList.add(new UserView(user));
        }
        return userViewList;
    }

    @Transactional(readOnly = true)
    public List<UserRoleView> getUserRoles() {
        List<UserRole> userRoleList=userDAO.getUserRoles();
        List<UserRoleView> userRoleViewList=new ArrayList();
        for (UserRole userRole:userRoleList){
            userRoleViewList.add(new UserRoleView(userRole));
        }
        return userRoleViewList;
    }

    @Transactional
    public void deleteUser(UserView user) {
        userDAO.deleteUser(userFromView(user));
    }

    @Transactional
    public void deleteUserRole(UserRoleView userRole) {
        userDAO.deleteUserRole(userRoleFromView(userRole));
    }
    
    public User userFromView(UserView userV){
        User user=null;
        if (!userV.getUsername().isEmpty()) {
            user = userDAO.getUserByName(userV.getUsername());
        }
        if (user==null){
            user = new User();
        }
        user.setEnabled(userV.isEnabled());
        user.setUsername(userV.getUsername());
        Set<UserRole> roleSet=user.getUserRole();
        roleSet.clear();
        for (UserRoleView rv:userV.getUserRole()){
            UserRole r =null;
            if (rv.getUserRoleId()!=null && rv.getUserRoleId()>0){
                r=userDAO.getUserRole(rv.getUserRoleId());
            }else{
                r=new UserRole();
            }
            r.setRole(rv.getRole());
            r.setUserRoleId(rv.getUserRoleId());
            r.setUser(user);
            roleSet.add(r);
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
        userRole.setUser(userDAO.getUserByName(userRoleV.getUser()));
        userRole.setUserRoleId(userRoleV.getUserRoleId());
        return userRole;
    }
}

