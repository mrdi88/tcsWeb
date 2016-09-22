package com.avectis.transportcontrol.web.controller;

import com.avectis.transportcontrol.entity.QueueType;
import com.avectis.transportcontrol.facade.QueueFacade;
import com.avectis.transportcontrol.view.QueueView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.avectis.transportcontrol.exception.WrongInputException;
import com.avectis.transportcontrol.facade.CardFacade;
import com.avectis.transportcontrol.view.CardView;

/**
 *
 * @author ASaburov
 */
public class QueueController extends AbstractController {
    
    private CardFacade cardFacade;
    private QueueFacade queueFacade;


    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }

    public void setQueueFacade(QueueFacade queueFacade) {
        this.queueFacade = queueFacade;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        if (arg0.getParameter("cmd")!=null){
            switch (arg0.getParameter("cmd")){
                case "add":
                    addNewQueue(arg0);
                    arg1.sendRedirect("queue");
                    break;
                case "delete":
                    deleteQueue(arg0);
                    arg1.sendRedirect("queue");
                    break;
                case "addCardToQueue":
                    addCardToQueue(arg0);
                    arg1.sendRedirect("queue");
                    break;
                case "deleteCardFromQueue":
                    deleteCardFromQueue(arg0);
                    arg1.sendRedirect("queue");
                    break;
                case "moveCardFromTo":
                    moveCardFromTo(arg0);
                    arg1.sendRedirect("queue");
                    break;
            }
        }
        if (arg0.getParameter("view")!=null){
            switch (arg0.getParameter("view")){
                case "":
            }
        }
        List<QueueView> qList = queueFacade.getQueueList();
        Map<String,List<QueueView>>  data = new HashMap<>();
        data.put("queueList", qList);
        return new ModelAndView("queue/queueList", data);
    }
    private void addNewQueue(HttpServletRequest arg0){
        if (arg0.getParameter("queueName")!=null);
        QueueView qv = new QueueView();
        qv.setName(arg0.getParameter("queueName"));
        qv.setType(QueueType.DOCK);
        qv.setCards(new ArrayList());
        qv.setId(queueFacade.addQueue(qv));
    }
    private void deleteQueue(HttpServletRequest arg0) throws WrongInputException{
        if (arg0.getParameter("queueId")==null) throw new WrongInputException("queueId not defined");
        QueueView qv=queueFacade.getQueue(Long.decode(arg0.getParameter("queueId")));
        if (qv!=null){
            queueFacade.delete(qv);
        }
    }
    private void addCardToQueue(HttpServletRequest arg0) throws WrongInputException{
        if (arg0.getParameter("queueId")==null) throw new WrongInputException("queueId not defined");
        if (arg0.getParameter("cardId")==null) throw new WrongInputException("cardId not defined");
        QueueView qv = queueFacade.getQueue(Long.decode(arg0.getParameter("queueId")));
        if (qv==null) throw new WrongInputException("queueId not found");
        CardView cv = cardFacade.getCard(Long.decode(arg0.getParameter("cardId")));
        if (cv==null) throw new WrongInputException("cardId not found");
        qv.getCards().add(cv);
        queueFacade.update(qv);
        
    }
    private void deleteCardFromQueue(HttpServletRequest arg0) throws WrongInputException{
        if (arg0.getParameter("queueId")==null) throw new WrongInputException("queueId not defined");
        if (arg0.getParameter("cardId")==null) throw new WrongInputException("cardId not defined");
        QueueView qv = queueFacade.getQueue(Long.decode(arg0.getParameter("queueId")));
        if (qv==null) throw new WrongInputException("queueId not found");
        CardView cv = cardFacade.getCard(Long.decode(arg0.getParameter("cardId")));
        if (cv==null) throw new WrongInputException("CardId not found");
        qv.getCards().remove(cv);
        queueFacade.update(qv);
    }
    private void moveCardFromTo(HttpServletRequest arg0) throws WrongInputException{
        if (arg0.getParameter("fromId")==null) throw new WrongInputException("fromId not defined");
        if (arg0.getParameter("toId")==null) throw new WrongInputException("toId not defined");
        if (arg0.getParameter("cardId")==null) throw new WrongInputException("cardId not defined");
        QueueView qvFrom = queueFacade.getQueue(Long.decode(arg0.getParameter("fromId")));
        if (qvFrom==null) throw new WrongInputException("fromId not found");
        QueueView qvTo = queueFacade.getQueue(Long.decode(arg0.getParameter("toId")));
        if (qvFrom==null) throw new WrongInputException("toId not found");
        CardView cv = cardFacade.getCard(Long.decode(arg0.getParameter("cardId")));
        if (cv==null) throw new WrongInputException("cardId not found");
        queueFacade.moveCard(cv, qvFrom, qvTo);
    }
}
