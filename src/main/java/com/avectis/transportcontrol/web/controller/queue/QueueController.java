package com.avectis.transportcontrol.web.controller.queue;

import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.QueueView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class QueueController extends AbstractController {
    
    private QueueFacade queueFacade;

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "getQueueList": //by scanner
                    return doGetQueueListCmd(arg0);
                default:
                    Map<String,String>  data;
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("queue/json/resultJSON", data);
            }
        }
        //do action
        String action = getAction(arg0.getRequestURI().toString());
        switch(action){
            case "queueList":
                return doQueueListAction(arg0);
        }
        arg1.sendRedirect("queue/queueList");
        return null;
    }
    private ModelAndView doGetQueueListCmd(HttpServletRequest arg0) throws JsonProcessingException{
        Map<String,String>  data;
        data = new HashMap<>();
        List<QueueView> ql = queueFacade.getQueueList();
        ObjectMapper mapper = new ObjectMapper();
        String queueJson = mapper.writeValueAsString(ql);
        data.put("queues", queueJson);
        return new ModelAndView("queue/json/queueListJSON", data);
    }
    private ModelAndView doQueueListAction(HttpServletRequest arg0){
        return new ModelAndView("queue/queueList", null);
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
