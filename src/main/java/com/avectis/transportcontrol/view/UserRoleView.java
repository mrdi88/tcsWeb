/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.view;

import com.avectis.transportcontrol.entity.*;
import com.avectis.transportcontrol.util.Role;
import java.util.Objects;

/**
 *
 * @author Dima
 */
public class UserRoleView{      
    private Long userRoleId;
    private String user;
    private Role role;

    public UserRoleView() {
    }
    
    public UserRoleView(UserRole userRole) {
        this.setUserRoleId(userRole.getUserRoleId());
        this.setRole(userRole.getRole());
        this.setUser(userRole.getUser().getUsername());
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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