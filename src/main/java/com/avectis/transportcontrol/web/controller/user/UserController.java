package com.avectis.transportcontrol.web.controller.user;

import com.avectis.transportcontrol.facade.UserFacade;
import com.avectis.transportcontrol.util.Role;
import com.avectis.transportcontrol.view.UserView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "getUsersList": //by scanner
                    return doGetUsersListCmd(arg0);
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
        Map<String,String>  data;
        data = new HashMap<>();
        List<UserView> usersList = userFacade.getUsers();
        ObjectMapper mapper = new ObjectMapper();
        String queueJson = mapper.writeValueAsString(usersList);
        data.put("users", queueJson);
        return new ModelAndView("user/json/userListJSON", data);
    }
    private ModelAndView doLoginAction(HttpServletRequest arg0){
        return new ModelAndView("user/login", null);
    }
    private ModelAndView doAccessDeniedAction(HttpServletRequest arg0){
        return new ModelAndView("user/accessdenied", null);
    }
    private ModelAndView doManageAction(HttpServletRequest arg0){
        List<String> roleList = new ArrayList();
        for (Role role:Role.values()){
            roleList.add(role.toString());
        }
        Map<String,List<String>>  data = new HashMap<>();;
        data.put("roles", roleList);
        return new ModelAndView("user/manage", data);
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
