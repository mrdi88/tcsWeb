/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Dima
 */

public class UserView {

    private String username;
    private String password;
    private boolean enabled;
    private Set<UserRoleView> userRole = new HashSet<UserRoleView>(0);

    public UserView() {
    }
    
    public UserView(User user) {
        this.setEnabled(user.isEnabled());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        //set RoleViewList
        Set<UserRoleView> UserRoleV=new HashSet<>();
        if (user.getUserRole()!=null){
            Set<UserRole> userRoles=user.getUserRole();
            for (UserRole userRole:userRoles){
                UserRoleView rv= new UserRoleView();
                rv.setUserRoleId(userRole.getUserRoleId());
                rv.setRole(userRole.getRole());
                rv.setUser(this.username);
                UserRoleV.add(rv);
            }
        }
        this.userRole = UserRoleV;
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRoleView> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRoleView> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", enabled=" + enabled + ", userRole=" + this.getUserRole() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.getUsername());
        hash = 59 * hash + Objects.hashCode(this.getPassword());
        hash = 59 * hash + (this.isEnabled() ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.getUserRole());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserView)) {
            return false;
        }
        final UserView other = (UserView) obj;
        if (this.isEnabled() != other.isEnabled()) {
            return false;
        }
        if (!Objects.equals(this.getUsername(), other.getUsername())) {
            return false;
        }
        if (!Objects.equals(this.getPassword(), other.getPassword())) {
            return false;
        }
        if (!Objects.equals(this.getUserRole(), other.getUserRole())) {
            return false;
        }
        return true;
    }

	
}
