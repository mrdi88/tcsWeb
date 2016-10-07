/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.*;
import java.util.Objects;

/**
 *
 * @author Dima
 */
public class UserRoleView{      
    private Long userRoleId;
    private UserView user;
    private String role;

    public UserRoleView() {
    }
    
    public UserRoleView(UserRole userRole) {
        this.setUserRoleId(userRole.getUserRoleId());
        this.setRole(userRole.getRole());
        this.setUser(new UserView(userRole.getUser()));
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public UserView getUser() {
        return user;
    }

    public void setUser(UserView user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" + "userRoleId=" + userRoleId + ", user=" + this.getUser() + ", role=" + role + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.userRoleId);
        hash = 41 * hash + Objects.hashCode(this.getUser());
        hash = 41 * hash + Objects.hashCode(this.role);
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
        if (!(obj instanceof UserRoleView)) {
            return false;
        }
        final UserRoleView other = (UserRoleView) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.userRoleId, other.userRoleId)) {
            return false;
        }
        if (!Objects.equals(this.getUser(), other.getUser())) {
            return false;
        }
        return true;
    }

}