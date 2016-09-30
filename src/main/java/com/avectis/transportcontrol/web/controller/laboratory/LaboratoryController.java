package com.avectis.transportcontrol.web.controller.laboratory;

import com.avectis.transportcontrol.control.scanner.CardScannerListener;
import com.avectis.transportcontrol.entity.QueueType;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.facade.ScannerFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.QueueNameView;
import com.avectis.transportcontrol.view.QueueView;
import com.avectis.transportcontrol.view.SampleView;
import com.avectis.transportcontrol.web.controller.card.CardController;
import com.avectis.transportcontrol.web.controller.card.CardNumberClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
/**
 *
 * @author ASaburov
 */
public class LaboratoryController extends AbstractController {

    private CardFacade cardFacade;
    private QueueFacade queueFacade;
    private ScannerFacade scannerFacade;
    private String cardScannerName;
    private String bufferQueue="Buffer";

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
            Map<String,String>  data;
            ObjectMapper mapper;
            String cardJson;
            switch (arg0.getParameter("cmd")){
                case "assignSample":
                    data = new HashMap<>();
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
                        }
                    } else {
                        data.put("result", "false");
                    }
                    return new ModelAndView("laboratory/json/resultJSON", data);
                case "getExistCardData": //by scanner
                    data = new HashMap<>();
                    CardNumberClass ecn = getCardNumber();
                    CardView card = cardFacade.getCardByNumber(ecn.getCardNumber());
                    mapper = new ObjectMapper();
                    cardJson = mapper.writeValueAsString(card);
                    data.put("card", cardJson);
                    return new ModelAndView("laboratory/json/existCardDataJSON", data);
                case "assignParams": 
                    data = new HashMap<>();
                    if (assignParams(arg0)){
                        data.put("result", "true");
                    }
                    else {
                        data.put("result", "false");
                    }
                    return new ModelAndView("laboratory/json/resultJSON", data);
                case "getAssignedCards": //by scanner
                    data = new HashMap<>();
                    List<CardView> cl = getAssignedCards();
                    mapper = new ObjectMapper();
                    cardJson = mapper.writeValueAsString(cl);
                    data.put("cards", cardJson);
                    return new ModelAndView("laboratory/json/assignedCardsJSON", data);
                default:
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("laboratory/json/resultJSON", data);
            }
        }
        //do action
        //get action 
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
                Logger.getLogger(CardController.class.getName()).log(Level.SEVERE, null, ex);
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
    private String getAction(String url){
        String action="";
        String[] URLparts = url.split("/", 0);
        if (URLparts.length==4){
            action=URLparts[3];
        }
        return action;
    }
    private ModelAndView doManageAction(HttpServletRequest arg0){
        List<QueueNameView> ql= queueFacade.getQueueNameList(QueueType.DOCK);
        Map<String,List<QueueNameView>>  qlData = new HashMap<>();;
        qlData.put("queueList", ql);
        return new ModelAndView("laboratory/manageSamples", qlData);
    }
    private ModelAndView doAssignAction(HttpServletRequest arg0){
        Map<String,String>  data = new HashMap<>();
        return new ModelAndView("laboratory/assignSample", data);
    }
}
