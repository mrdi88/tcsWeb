package com.avectis.transportcontrol.web.controller.dock;

import com.avectis.transportcontrol.control.barrier.Barrier;
import com.avectis.transportcontrol.control.infotable.InfoTable;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.CargoView;
import com.avectis.transportcontrol.view.QueueView;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author ASaburov
 */
public class DockController extends AbstractController {
    
    private QueueFacade queueFacade;
    private CardFacade cardFacade;
    String firstQueueName;
    String secondQueueName;
    //infodisplay
    InfoTable infoTable;
    //control barrier
    Barrier barrier;

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setFirstQueueName(String firstQueueName) {
        this.firstQueueName = firstQueueName;
    }

    public void setSecondQueueName(String secondQueueName) {
        this.secondQueueName = secondQueueName;
    }

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    public void setInfoTable(InfoTable infoTable) {
        this.infoTable = infoTable;
    }

    public void setBarrier(Barrier barrier) {
        this.barrier = barrier;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        Map<String,String>  data;
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "getDocksData": //by scanner
                    data = new HashMap<>();
                    List<QueueView> ql = new ArrayList<>();
                    //get docks queue
                    ql.add(getDockQueue(firstQueueName));
                    ql.add(getDockQueue(secondQueueName));
                    //put to JSON
                    ObjectMapper mapper = new ObjectMapper();
                    String queueJson = mapper.writeValueAsString(ql);
                    data.put("queues", queueJson);
                    return new ModelAndView("dock/json/docksQueueDataJSON", data);
                case "callCar":
                    data = new HashMap<>();
                    if (callCar(arg0)){
                        data.put("result", "true");
                    }else{
                        data.put("result", "false");
                    }
                    return new ModelAndView("queue/json/resultJSON", data);
                case "acceptCar":
                    data = new HashMap<>();
                    if (acceptCar(arg0)){
                        callCar(arg0); //remove from this command
                        data.put("result", "true");
                    }else{
                        data.put("result", "false");
                    }
                    return new ModelAndView("queue/json/resultJSON", data);
                case "releaseCar":
                    data = new HashMap<>();
                    if (releaseCar(arg0)){
                        data.put("result", "true");
                    }else{
                        data.put("result", "false");
                    }
                    return new ModelAndView("queue/json/resultJSON", data);
                default:
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("queue/json/resultJSON", data);
            }
        }
        //do action
        //get action 
        String action = getAction(arg0.getRequestURI().toString());
        switch(action){
            case "manage":
                return doManageAction(arg0);
        }
        arg1.sendRedirect("dock/manage");
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
    private ModelAndView doManageAction(HttpServletRequest arg0){
        return new ModelAndView("dock/manage", null);
    }
    private QueueView getDockQueue(String name){
        QueueView qv = queueFacade.getQueueByName(name);
        return qv;
    }
    private boolean callCar(HttpServletRequest arg0){
        if (arg0.getParameter("cardId")!=null && !arg0.getParameter("cardId").isEmpty()){
            CardView card=cardFacade.getCard(Long.parseLong(arg0.getParameter("cardId")));
            //display on infotable
            String[] textArray=new String[1];
            textArray[0]=card.getCar().getCarNumber();
            infoTable.SendData(textArray);
            infoTable.SetBrightness(1);
            //open barier
            barrier.Open();
            
            return true;
        }
        return false;
    }
    private boolean acceptCar(HttpServletRequest arg0){
        if (arg0.getParameter("cardId")!=null && !arg0.getParameter("cardId").isEmpty()){
            CardView card=cardFacade.getCard(Long.parseLong(arg0.getParameter("cardId")));
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            CargoView cargo = card.getCar().getCargo();
            cargo.setDischargeDate(new Date());
            cargo.setDischargingPlace(arg0.getParameter("queueName"));
            cardFacade.update(card);
            return true;
        }
        return false;
    }
    private boolean releaseCar(HttpServletRequest arg0){
        if (arg0.getParameter("cardId")!=null && !arg0.getParameter("cardId").isEmpty()){
            CardView card=cardFacade.getCard(Long.parseLong(arg0.getParameter("cardId")));
            QueueView queue = queueFacade.getQueueByName(arg0.getParameter("queueName"));
            queueFacade.deleteCardFromQueue(queue, card);
            return true;
        }
        return false;
    }
}
