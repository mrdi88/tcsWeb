/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.util;

import com.avectis.transportcontrol.facade.UserFacade;
import com.avectis.transportcontrol.view.UserRoleView;
import com.avectis.transportcontrol.view.UserView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Dima
 */
public class MyUserDetailsService implements UserDetailsService {

    private UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(final String username)
           throws UsernameNotFoundException {

            UserView user = userFacade.getUserByName(username);
            List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

            return buildUserForAuthentication(user, authorities);
    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(UserView user,
            List<GrantedAuthority> authorities) {
            return new User(user.getUsername(),
                    user.getPassword(), user.isEnabled(),
                    true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRoleView> userRoles) {

            Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

            // Build user's authorities
            for (UserRoleView userRole : userRoles) {
                    setAuths.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
            }

            List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

            return Result;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

}
