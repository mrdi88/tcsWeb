package com.avectis.transportcontrol.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author ASaburov
 */
public class UserController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        
        //get action 
        String action = getAction(arg0.getRequestURI().toString());
        switch(action){
            case "login":
                return doLoginAction(arg0);
            case "accessdenied":
                return doAccessDeniedAction(arg0);
        }
        arg1.sendRedirect("user/login");
        return null;
    }
    private String getAction(String url){
        String action="";
        String[] URLparts = url.split("/", 0);
        if (URLparts.length==4){
            action=URLparts[3];
        }
        return action;
    }
    private ModelAndView doLoginAction(HttpServletRequest arg0){
        return new ModelAndView("user/login", null);
    }
    private ModelAndView doAccessDeniedAction(HttpServletRequest arg0){
        return new ModelAndView("user/accessdenied", null);
    }
}
