package com.avectis.transportcontrol.web.controller.laboratory;

import com.avectis.transportcontrol.entity.QueueType;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.QueueNameView;
import com.avectis.transportcontrol.view.QueueView;
import com.avectis.transportcontrol.view.SampleView;
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
 * @author ASaburov
 */
public class ManageSamplesController extends AbstractController {

    private CardFacade cardFacade;
    private QueueFacade queueFacade;
    private String bufferQueue="Buffer";

    public void setBufferQueue(String bufferQueue) {
        this.bufferQueue = bufferQueue;
    }
    
    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        Map<String,String>  data;
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "assignParams": 
                    data = new HashMap<>();
                    if (assignParams(arg0)){
                        data.put("result", "true");
                    }
                    else {
                        data.put("result", "false");
                    }
                    return new ModelAndView("laboratory/resultJSON", data);
                case "getAssignedCards": //by scanner
                    data = new HashMap<>();
                    List<CardView> cl = getAssignedCards();
                    ObjectMapper mapper = new ObjectMapper();
                    String cardJson = mapper.writeValueAsString(cl);
                    data.put("cards", cardJson);
                    return new ModelAndView("laboratory/assignedCardsJSON", data);
                default:
                    data = new HashMap<>();
                    data.put("result", "cmd not found");
                    return new ModelAndView("laboratory/resultJSON", data);
            }
        }
        List<QueueNameView> ql= queueFacade.getQueueNameList(QueueType.DOCK);
        Map<String,List<QueueNameView>>  qlData = new HashMap<>();;
        qlData.put("queueList", ql);
        return new ModelAndView("laboratory/manageSamples", qlData);
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
                arg0.getParameter("weediness")!=null && 
                arg0.getParameter("weediness").length()>0 &&
                arg0.getParameter("gluten")!=null && 
                arg0.getParameter("gluten").length()>0 &&
                arg0.getParameter("humidity")!=null && 
                arg0.getParameter("humidity").length()>0 &&
                arg0.getParameter("queueId")!=null && 
                arg0.getParameter("queueId").length()>0){
                
                CardView card = cardFacade.getCard(Long.decode(arg0.getParameter("cardId")));
                QueueView qv=null;
                SampleView sample;
                if (card!=null){
                    sample=card.getCar().getCargo().getSample();
                    sample.setWeediness(Float.parseFloat(arg0.getParameter("weediness")));
                    sample.setGluten(Float.parseFloat(arg0.getParameter("gluten")));
                    sample.setHumidity(Float.parseFloat(arg0.getParameter("humidity")));
                    //назначить очередь
                    if (arg0.getParameter("queueId").equals("0")){
                        queueFacade.deleteCardFromQueues(card);
                        card.getCar().setDestination("");
                        cardFacade.update(card);
                        return true;
                    }
                    qv = queueFacade.getQueue(Long.decode(arg0.getParameter("queueId")));
                    if (qv!=null ){
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
}
