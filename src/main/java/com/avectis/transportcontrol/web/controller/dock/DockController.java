package com.avectis.transportcontrol.web.controller.dock;

import com.avectis.transportcontrol.control.barrier.Barrier;
import com.avectis.transportcontrol.control.infotable.InfoTable;
import com.avectis.transportcontrol.control.trafficLight.TrafficLight;
import com.avectis.transportcontrol.exception.ConnectionFailException;
import com.avectis.transportcontrol.facade.BarrierFacade;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.InfoTableFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.facade.TrafficLightFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.CargoView;
import com.avectis.transportcontrol.view.QueueView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author Dima
 */
public class DockController extends AbstractController {
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DockController.class.getName());
    
    private QueueFacade queueFacade;
    private CardFacade cardFacade;
    private InfoTableFacade infoTableFacade;
    private BarrierFacade barrierFacade;
    private String firstQueueName;
    private String secondQueueName;
    //infodisplay
    private String firstQueueinfoTableName;
    private String secondQueueinfoTableName;
    //control barrier
    private String firstQueuebarrierName;
    private String secondQueuebarrierName;
    //light
    private TrafficLightFacade lightFacade;
    private String dock1OutLightName;
    private String dock2OutLightName;
    private String crossRoadLightName;

    public void setLightFacade(TrafficLightFacade lightFacade) {
        this.lightFacade = lightFacade;
    }

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

    public void setInfoTableFacade(InfoTableFacade infoTableFacade) {
        this.infoTableFacade = infoTableFacade;
    }

    public void setBarrierFacade(BarrierFacade barrierFacade) {
        this.barrierFacade = barrierFacade;
    }

    public void setFirstQueueinfoTableName(String firstQueueinfoTableName) {
        this.firstQueueinfoTableName = firstQueueinfoTableName;
    }

    public void setSecondQueueinfoTableName(String secondQueueinfoTableName) {
        this.secondQueueinfoTableName = secondQueueinfoTableName;
    }

    public void setFirstQueuebarrierName(String firstQueuebarrierName) {
        this.firstQueuebarrierName = firstQueuebarrierName;
    }

    public void setSecondQueuebarrierName(String secondQueuebarrierName) {
        this.secondQueuebarrierName = secondQueuebarrierName;
    }

    public void setDock1OutLightName(String dock1OutLightName) {
        this.dock1OutLightName = dock1OutLightName;
    }

    public void setDock2OutLightName(String dock2OutLightName) {
        this.dock2OutLightName = dock2OutLightName;
    }

    public void setCrossRoadLightName(String crossRoadLightName) {
        this.crossRoadLightName = crossRoadLightName;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "getDocksData": //by scanner
                    return doGetDocksDataCmd(arg0);
                case "callCar": //show car number in info table amd open barrier
                    return doCallCarCmd(arg0);
                case "resetCall": //clear info table amd close barrier
                    return doResetCallCmd(arg0);
                case "acceptCar":
                    return doAcceptCarCmd(arg0);
                case "releaseCar":
                    return doReleaseCarCmd(arg0);
                default:
                    Map<String,String>  data;
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("queue/json/resultJSON", data);
            }
        }
        //do action 
        String action = getAction(arg0.getRequestURI());
        switch(action){
            case "manage":
                return doManageAction(arg0);
        }
        arg1.sendRedirect("dock/manage");
        return null;
    }
    private ModelAndView doGetDocksDataCmd(HttpServletRequest arg0) throws JsonProcessingException {
        Map<String,String>  data = new HashMap<>();
        List<QueueView> ql = new ArrayList<>();
        try{
            //get docks queue
            ql.add(getDockQueue(firstQueueName));
            ql.add(getDockQueue(secondQueueName));
            //put to JSON
            ObjectMapper mapper = new ObjectMapper();
            String queueJson = mapper.writeValueAsString(ql);
            data.put("queues", queueJson);
        }catch(Exception ex){
            logger.error("doGetDocksDataCmd: "+ex.getMessage());
        }
        return new ModelAndView("dock/json/docksQueueDataJSON", data);
    }
    private ModelAndView doCallCarCmd(HttpServletRequest arg0) {
        Map<String,String>  data = new HashMap<>();
        try{
            if (callCar(arg0)){
                data.put("result", "true");
            }else{
                data.put("result", "false");
            }
        }catch(Exception ex){
            data.put("result", "false");
            logger.error("doCallCarCmd: "+ex.getMessage());
        }
        return new ModelAndView("queue/json/resultJSON", data);
    }
    private ModelAndView doResetCallCmd(HttpServletRequest arg0) {
        Map<String,String>  data = new HashMap<>();
        try{
            if (resetCall(arg0)){
                data.put("result", "true");
            }else{
                data.put("result", "false");
            }
        }catch(Exception ex){
            data.put("result", "false");
            logger.error("doResetCallCmd: "+ex.getMessage());
        }
        return new ModelAndView("queue/json/resultJSON", data);    
    }
    private ModelAndView doAcceptCarCmd(HttpServletRequest arg0) {
        Map<String,String>  data = new HashMap<>();
        try{
            if (acceptCar(arg0)){
                data.put("result", "true");
            }else{
                data.put("result", "false");
            }
        }catch(Exception ex){
            data.put("result", "false");
            logger.error("doAcceptCarCmd: "+ex.getMessage());
        }
        return new ModelAndView("queue/json/resultJSON", data);
    }
    private ModelAndView doReleaseCarCmd(HttpServletRequest arg0){
        Map<String,String>  data = new HashMap<>();
        try{
            if (releaseCar(arg0)){
                data.put("result", "true");
            }else{
                data.put("result", "false");
            }
        }catch(Exception ex){
            data.put("result", "false");
            logger.error("doReleaseCarCmd: "+ex.getMessage());
        }
        return new ModelAndView("queue/json/resultJSON", data);
    }
    private ModelAndView doManageAction(HttpServletRequest arg0){
        return new ModelAndView("dock/manage", null);
    }
    private String getAction(String url){
        String action="";
        String[] URLparts = url.split("/", 0);
        if (URLparts.length==4){
            action=URLparts[3];
        }
        return action;
    }
    private QueueView getDockQueue(String name){
        QueueView qv = queueFacade.getQueueByName(name);
        return qv;
    }
    private boolean callCar(HttpServletRequest arg0) throws ConnectionFailException{
        if (arg0.getParameter("cardId")!=null && !arg0.getParameter("cardId").isEmpty() &&
            arg0.getParameter("queueName")!=null && !arg0.getParameter("queueName").isEmpty()){
            if (Objects.equals(arg0.getParameter("queueName"),firstQueueName)){
                CardView card=cardFacade.getCard(Long.parseLong(arg0.getParameter("cardId")));
                //display on infotable
                List<String> textArray=new ArrayList();
                textArray.add(card.getCar().getCarNumber());
                InfoTable infoTable = infoTableFacade.GetElementById(firstQueueinfoTableName);
                infoTable.SendData(textArray);
                //set brightness level
                infoTable.SetBrightness(1);
                //open barier
                Barrier barrier=barrierFacade.GetElementById(firstQueuebarrierName);
                barrier.Open();
                //turn red light on exit
                TrafficLight dockLight = lightFacade.GetElementById(dock1OutLightName);
                dockLight.TurnRed();
                return true;
            }else
            if (Objects.equals(arg0.getParameter("queueName"),secondQueueName)){
                CardView card=cardFacade.getCard(Long.parseLong(arg0.getParameter("cardId")));
                //display on infotable
                List<String> textArray=new ArrayList();
                textArray.add(card.getCar().getCarNumber());
                InfoTable infoTable = infoTableFacade.GetElementById(secondQueueinfoTableName);
                infoTable.SendData(textArray);
                //set brightness level
                infoTable.SetBrightness(1);
                //open barier
                Barrier barrier=barrierFacade.GetElementById(secondQueuebarrierName);
                barrier.Open();
                //turn red light on exit
                TrafficLight dockLight = lightFacade.GetElementById(dock2OutLightName);
                dockLight.TurnRed();
                return true;
            }
        }  
        return false;
    }
    private boolean resetCall(HttpServletRequest arg0) throws ConnectionFailException{
        if (arg0.getParameter("queueName")!=null && !arg0.getParameter("queueName").isEmpty()){
            if (Objects.equals(arg0.getParameter("queueName"),firstQueueName)){
                //display on infotable
                List<String> textArray=new ArrayList();
                textArray.add(" ");
                InfoTable infoTable = infoTableFacade.GetElementById(firstQueueinfoTableName);
                infoTable.SendData(textArray);
                //set brightness level
                infoTable.SetBrightness(1);
                //open barier
                Barrier barrier=barrierFacade.GetElementById(firstQueuebarrierName);
                barrier.Close();
                return true;
            }else
            if (Objects.equals(arg0.getParameter("queueName"),secondQueueName)){
                //display on infotable
                List<String> textArray=new ArrayList();
                textArray.add(" ");
                InfoTable infoTable = infoTableFacade.GetElementById(secondQueueinfoTableName);
                infoTable.SendData(textArray);
                //set brightness level
                infoTable.SetBrightness(1);
                //open barier
                Barrier barrier=barrierFacade.GetElementById(secondQueuebarrierName);
                barrier.Close();
                return true;
            }
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
            if (arg0.getParameter("queueName")!=null && !arg0.getParameter("queueName").isEmpty()){
                if (Objects.equals(arg0.getParameter("queueName"),firstQueueName)){
                    //turn green light on exit
                    TrafficLight dockLight = lightFacade.GetElementById(dock1OutLightName);
                    dockLight.TurnGreen();
                }else
                if (Objects.equals(arg0.getParameter("queueName"),secondQueueName)){
                    //turn green light on exit
                    TrafficLight dockLight = lightFacade.GetElementById(dock2OutLightName);
                    dockLight.TurnGreen();
                }
                //turn red crossroad for 3 min
                int time = 1800; //3 min
                TrafficLight crossLight = lightFacade.GetElementById(crossRoadLightName);
                crossLight.TurnRed();
                TimerTask timerTask=new TimerTask(){
                    @Override
                    public void run() 
                    {   
                        TrafficLight crossLight = lightFacade.GetElementById(crossRoadLightName);
                        crossLight.TurnGreen();
                    }
                };
                new Timer().schedule(timerTask, time);
                //delete card from queues
                CardView card=cardFacade.getCard(Long.parseLong(arg0.getParameter("cardId")));
                queueFacade.deleteCardFromQueues(card);
                return true;
            }
        }
        return false;
    }
}
