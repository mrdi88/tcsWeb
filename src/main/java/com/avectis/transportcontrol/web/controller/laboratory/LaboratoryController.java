package com.avectis.transportcontrol.web.controller.laboratory;

import com.avectis.transportcontrol.control.scanner.CardScannerListener;
import com.avectis.transportcontrol.control.trafficLight.TrafficLight;
import com.avectis.transportcontrol.util.QueueType;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.facade.ScannerFacade;
import com.avectis.transportcontrol.facade.TrafficLightFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.QueueNameView;
import com.avectis.transportcontrol.view.QueueView;
import com.avectis.transportcontrol.view.SampleView;
import com.avectis.transportcontrol.web.controller.card.CardNumberClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author Dima
 */
public class LaboratoryController extends AbstractController {
    static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LaboratoryController.class.getName());

    private CardFacade cardFacade;
    private QueueFacade queueFacade;
    private ScannerFacade scannerFacade;
    private String cardScannerName;
    private String bufferQueue="Buffer";
    //light
    private TrafficLightFacade lightFacade;
    private String inScalesInLightName;

    public void setLightFacade(TrafficLightFacade lightFacade) {
        this.lightFacade = lightFacade;
    }

    public void setInScalesInLightName(String inScalesInLightName) {
        this.inScalesInLightName = inScalesInLightName;
    }

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    public String getCardScannerName() {
        return cardScannerName;
    }

    public void setCardScannerName(String newCardScannerName) {
        this.cardScannerName = newCardScannerName;
    }

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setScannerFacade(ScannerFacade scannerFacade) {
        this.scannerFacade = scannerFacade;
    }
    public void setBufferQueue(String bufferQueue) {
        this.bufferQueue = bufferQueue;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        if (arg0.getParameter("cmd")!=null){ 
            switch (arg0.getParameter("cmd")){
                case "assignSample":
                    return doAssignSampleCmd(arg0);
                case "getExistCardData": //by scanner
                    return doGetExistCardDataCmd(arg0);
                case "assignParams": 
                    return doAssignParamsCmd(arg0);
                case "getAssignedCards": //by scanner
                    return doGetAssignedCardsCmd(arg0);
                default:
                    Map<String,String>  data;
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("laboratory/json/resultJSON", data);
            }
        }
        //do action
        String action = getAction(arg0.getRequestURI().toString());
        switch(action){
            case "manage":
                return doManageAction(arg0);
            case "assign":
                return doAssignAction(arg0);
        }
        arg1.sendRedirect("laboratory/manage");
        return null;
    }
    private ModelAndView doAssignSampleCmd(HttpServletRequest arg0){
        Map<String,String>  data = new HashMap<>();
        try{
            if (arg0.getParameter("cardId")!=null && 
                    arg0.getParameter("cardId").length()>0 &&
                    arg0.getParameter("sampleName")!=null && 
                    arg0.getParameter("sampleName").length()>0){
                CardView card = cardFacade.getCard(Long.decode(arg0.getParameter("cardId")));
                if (card!=null){
                    SampleView sample=new SampleView();
                    sample.setName(arg0.getParameter("sampleName"));
                    card.getCar().getCargo().setSample(sample);
                    cardFacade.update(card);
                    data.put("result", "true");
                    turnGreenLightWeight(); // show green light in weight module
                }
            } else {
                data.put("result", "false");
            }
        }catch(Exception ex){
            data.put("result", "false");
            logger.error("doAssignSampleCmd: "+ex.getMessage());
        }
        return new ModelAndView("laboratory/json/resultJSON", data);
    }
    private ModelAndView doGetExistCardDataCmd(HttpServletRequest arg0) throws JsonProcessingException{
        Map<String,String>  data = new HashMap<>();
        try{
            CardNumberClass ecn = getCardNumber();
            CardView card = cardFacade.getCardByNumber(ecn.getCardNumber());
            ObjectMapper mapper = new ObjectMapper();
            String cardJson = mapper.writeValueAsString(card);
            data.put("card", cardJson);
        }catch(Exception ex){
            data.put("result", "false");
            logger.error("doGetExistCardDataCmd: "+ex.getMessage());
        }
        return new ModelAndView("laboratory/json/existCardDataJSON", data);
    }
    private ModelAndView doAssignParamsCmd(HttpServletRequest arg0){
        Map<String,String>  data = new HashMap<>();
        try{
            if (assignParams(arg0)){
                data.put("result", "true");
            }
            else {
                data.put("result", "false");
            }
        }catch(Exception ex){
            data.put("result", "false");
            logger.error("doAssignParamsCmd: "+ex.getMessage());
        }
        return new ModelAndView("laboratory/json/resultJSON", data);
    }
    private ModelAndView doGetAssignedCardsCmd(HttpServletRequest arg0) throws JsonProcessingException{
        Map<String,String>  data = new HashMap<>();
        try{
            List<CardView> cl = getAssignedCards();
            ObjectMapper mapper = new ObjectMapper();
            String cardJson = mapper.writeValueAsString(cl);
            data.put("cards", cardJson);
        }catch(Exception ex){
            logger.error("doGetAssignedCardsCmd: "+ex.getMessage());
        }
        return new ModelAndView("laboratory/json/assignedCardsJSON", data);
    }
    private ModelAndView doManageAction(HttpServletRequest arg0){
        Map<String,List<QueueNameView>>  qlData = new HashMap<>();
        try{
            List<QueueNameView> ql= queueFacade.getQueueNameList(QueueType.DOCK);
            qlData.put("queueList", ql);
        }catch(Exception ex){
            logger.error("doManageAction: "+ex.getMessage());
        }
        return new ModelAndView("laboratory/manageSamples", qlData);
    }
    private ModelAndView doAssignAction(HttpServletRequest arg0){
        Map<String,String>  data = new HashMap<>();
        return new ModelAndView("laboratory/assignSample", data);
    }
    private CardNumberClass getCardNumber(){
        CardNumberClass cardNumber= new CardNumberClass("0");
        synchronized(cardNumber){
            CardScannerListener cardListener=new CardScannerListener(){
                CardNumberClass cardNumber;
                public CardScannerListener setCardNumber(CardNumberClass cardNumber) {
                    this.cardNumber=cardNumber;
                    return this;
                }
                @Override
                public void onCardLogined(String CardNumberHEX,String CardNumberDEC){
                    synchronized(cardNumber){
                        cardNumber.setCardNumber(CardNumberDEC);
                        cardNumber.notifyAll();
                    }
                }
            }.setCardNumber(cardNumber);
            scannerFacade.getElementById(cardScannerName).addListener(cardListener);
            try {
                cardNumber.wait(60000);
                scannerFacade.getElementById(cardScannerName).removeListener(cardListener);
            } catch (InterruptedException ex) {
                logger.error("getCardNumber: "+ex.getMessage());
            }
        }
        return cardNumber;
    }
    private List<CardView> getAssignedCards(){
        List<CardView> cal= new ArrayList();
        List<CardView> cl = cardFacade.getList();
        for (CardView c: cl){
            if (c.getCar().getCargo().getSample()!=null){
                cal.add(c);
            }
        }
        return cal;
    }
    private boolean assignParams(HttpServletRequest arg0){
        if (arg0.getParameter("cardId")!=null && 
            arg0.getParameter("cardId").length()>0 &&
            arg0.getParameter("nomenclature")!=null && 
            arg0.getParameter("nomenclature").length()>0 &&
            arg0.getParameter("queueId")!=null && 
            arg0.getParameter("queueId").length()>0 &&
            arg0.getParameter("humidity")!=null && 
            arg0.getParameter("humidity").length()>0 &&
            arg0.getParameter("class")!=null && 
            arg0.getParameter("class").length()>0 &&
            arg0.getParameter("siloNumber")!=null && 
            arg0.getParameter("siloNumber").length()>0 ){

            CardView card = cardFacade.getCard(Long.decode(arg0.getParameter("cardId")));
            QueueView qv=null;
            SampleView sample;
            if (card!=null){
                sample=card.getCar().getCargo().getSample();
                sample.setNomenclature(arg0.getParameter("nomenclature"));
                sample.setCultureClass(arg0.getParameter("class"));
                sample.setHumidity(Float.parseFloat(arg0.getParameter("humidity")));
                card.getCar().setSiloNumber(arg0.getParameter("siloNumber"));
                //назначить очередь/ не назначена точка выгрузки
                if (arg0.getParameter("queueId").equals("0")){
                    queueFacade.deleteCardFromQueues(card);
                    card.getCar().setDestination("");
                    cardFacade.update(card);
                    return true;
                }
                qv = queueFacade.getQueue(Long.decode(arg0.getParameter("queueId")));
                if (qv!=null ){ //назначить очередь/ назначена точка выгрузки
                    if (!qv.getName().equals(card.getCar().getDestination())){
                        queueFacade.deleteCardFromQueues(card);
                        //set destination
                        qv = queueFacade.getQueue(Long.decode(arg0.getParameter("queueId")));
                        card.getCar().setDestination(qv.getName());

                        //put to buffer queue
                        qv = queueFacade.getQueueByName(bufferQueue);
                        qv.getCards().add(card);
                        queueFacade.update(qv);
                    }
                    cardFacade.update(card);
                    return true;
                }
            }
        }
        return false;
    }
    private void turnGreenLightWeight(){
        TrafficLight light = lightFacade.GetElementById(inScalesInLightName);
        light.TurnGreen();
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
