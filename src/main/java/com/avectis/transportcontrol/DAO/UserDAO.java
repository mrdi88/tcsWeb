/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.User;
import com.avectis.transportcontrol.entity.UserRole;
import java.util.List;

/**
 *
 * @author DPoplauski
 */
public interface UserDAO {
    public void addUser(User user);
    public Long addUserRole(UserRole userRole);
    public void update(User user);
    public void update(UserRole userRole);
    public User getUserByName(String username);
    public UserRole getUserRole(Long id);
    public List<User> getUsers();
    public List<UserRole> getUserRoles();
    public void deleteUser(User user);
    public void deleteUserRole(UserRole userRole);
}
