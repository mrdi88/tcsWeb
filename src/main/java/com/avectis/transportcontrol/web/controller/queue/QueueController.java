package com.avectis.transportcontrol.web.controller.queue;

import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.QueueView;
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
 * @author ASaburov
 */
public class QueueController extends AbstractController {
    
    private QueueFacade queueFacade;

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        Map<String,String>  data;
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "getQueueList": //by scanner
                    data = new HashMap<>();
                    List<QueueView> ql = queueFacade.getQueueList();
                    ObjectMapper mapper = new ObjectMapper();
                    String queueJson = mapper.writeValueAsString(ql);
                    data.put("queues", queueJson);
                    return new ModelAndView("queue/json/queueListJSON", data);
                default:
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("queue/json/resultJSON", data);
            }
        }
        return new ModelAndView("queue/queueList", null);
    }
}
