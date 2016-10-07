/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dima
 */
@Entity
@Table(name="user_roles")
public class UserRole{
    @Id        
    private Long userRoleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;
    private String role;

    public UserRole() {
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
        if (!(obj instanceof UserRole)) {
            return false;
        }
        final UserRole other = (UserRole) obj;
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