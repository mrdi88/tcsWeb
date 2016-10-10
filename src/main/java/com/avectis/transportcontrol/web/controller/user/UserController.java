package com.avectis.transportcontrol.web.controller.user;

import com.avectis.transportcontrol.facade.UserFacade;
import com.avectis.transportcontrol.util.Role;
import com.avectis.transportcontrol.view.UserRoleView;
import com.avectis.transportcontrol.view.UserView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author Dima
 */
public class UserController extends AbstractController {
    
    private UserFacade userFacade;

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "getUsersList": 
                    return doGetUsersListCmd(arg0);
                case "changeRoles": 
                    return doChangeRolesCmd(arg0);
                default:
                    Map<String,String>  data;
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("queue/json/resultJSON", data);
            }
        }
        //get action 
        String action = getAction(arg0.getRequestURI().toString());
        switch(action){
            case "login":
                return doLoginAction(arg0);
            case "accessdenied":
                return doAccessDeniedAction(arg0);
            case "manage":
                return doManageAction(arg0);
        }
        arg1.sendRedirect("user/login");
        return null;
    }
    private ModelAndView doGetUsersListCmd(HttpServletRequest arg0) throws JsonProcessingException{
        //set users list
        List<UserView> usersList = userFacade.getUsers();
        ObjectMapper mapper = new ObjectMapper();
        String usersJson = mapper.writeValueAsString(usersList);
        ModelAndView model = new ModelAndView();
        model.addObject("users", usersJson);
        //set roles list
        List<String> roleList = new ArrayList();
        for (Role role:Role.values()){
            roleList.add(role.toString());
        }
        String rolesJson = mapper.writeValueAsString(roleList);
        model.addObject("roles", rolesJson);
        model.setViewName("user/json/userListJSON");
        return model;
    }
    private ModelAndView doChangeRolesCmd(HttpServletRequest arg0) throws JsonProcessingException{
        ModelAndView model = new ModelAndView();
        //set users list
        String username= arg0.getParameter("username");
        String roles=arg0.getParameter("roles");
        UserView user=userFacade.getUserByName(username);
        if (user!=null){
            Set<UserRoleView> userRoles=user.getUserRole();
            //userRoles.clear();
            //userFacade.update(user);
            String[] rolesList = roles.split(",");
            //add new roles
            for (String role:rolesList){
                boolean existFlag=false;
                for (UserRoleView rolev:userRoles){
                    if (rolev.getRole().equals(Role.valueOf(role))){
                        existFlag=true;
                    }
                }
                if (!existFlag){
                    //add new role if not exist
                    UserRoleView roleView=new UserRoleView();
                    roleView.setUser(user.getUsername());
                    roleView.setRole(Role.valueOf(role));
                    userRoles.add(roleView);
                }
            }
            //delete not assigned roles
            Set<UserRoleView> notAssignedRoles=new HashSet();
            for (UserRoleView rolev:userRoles){
                boolean existFlag=false;
                for (String role:rolesList){
                    if (rolev.getRole().equals(Role.valueOf(role))){
                        existFlag=true;
                    }
                }
                if (!existFlag){
                    //add new role if not exist
                    notAssignedRoles.add(rolev);
                }
            }
            for (UserRoleView naRole:notAssignedRoles){
                userRoles.remove(naRole);
            }
            
            userFacade.update(user);
            model.addObject("result", "true");
        }else{
            model.addObject("result", "false");
        }
        model.setViewName("user/json/resultJSON");
        return model;
    }
    private ModelAndView doLoginAction(HttpServletRequest arg0){
        return new ModelAndView("user/login", null);
    }
    private ModelAndView doAccessDeniedAction(HttpServletRequest arg0){
        return new ModelAndView("user/accessdenied", null);
    }
    private ModelAndView doManageAction(HttpServletRequest arg0) throws JsonProcessingException{
        ModelAndView model = new ModelAndView();
        model.setViewName("user/manage");
        return model;
    }
    private String getAction(String url){
        String action="";
        String[] URLparts = url.split("/", 0);
        if (URLparts.length==4){
            action=URLparts[3];
        }
        return action;
    }
}
