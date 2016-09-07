/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.facade;

import com.avectis.transportcontrol.DAO.CardDAO;
import com.avectis.transportcontrol.DAO.QueueDAO;
import com.avectis.transportcontrol.entity.Card;
import com.avectis.transportcontrol.entity.Queue;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.QueueView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dima
 */
public class QueueFacade {
    
    private QueueDAO queueDAO;
    private CardDAO cardDAO;
    private CardFacade cardFacade;
    
    public QueueFacade() {
    }
    
    public void setQueueDAO(QueueDAO queueDAO) {
        this.queueDAO = queueDAO;
    }

    public void setCardDAO(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public void setCardFacade(CardFacade cardFacade) {
        this.cardFacade = cardFacade;
    }
    @Transactional
    public Long addQueue(QueueView queueView){
        return queueDAO.addQueue(queueFromView(queueView));
    }
    @Transactional(readOnly = true)
    public QueueView getQueue(Long id){
        return new QueueView(queueDAO.getQueue(id));
    }
    @Transactional(readOnly = true)
    public List<QueueView> getQueueList(){
        List<QueueView> qvList=new ArrayList<>();
        List<Queue> qList = queueDAO.getQueueList();
        for (Queue q:qList){
            qvList.add(new QueueView(q));
        }
        return qvList;
    }
    @Transactional
    public void update(QueueView queueView){
        queueDAO.update(queueFromView(queueView));
    }
    @Transactional
    public void delete(QueueView qv){
        queueDAO.deleteQueue(queueFromView(qv));
    }
    @Transactional
    public void deleteCardFromQueues(CardView cardv){
        Card card;
        if (cardv.getId() != null && cardv.getId() > 0) {
            card=cardDAO.getCard(cardv.getId());
        } else {
            return;
        }
        List<Queue> qList=queueDAO.getQueueList();
        for (Queue q:qList){
            if (q.getCards().remove(card)) queueDAO.update(q);
        }
    }
    @Transactional
    public void deleteCardFromQueue(QueueView queuev, CardView cardv){
        Card card;
        Queue queue;
        if ((cardv.getId() != null && cardv.getId() > 0) && (queuev.getId() != null && queuev.getId() > 0)) {
            card=cardDAO.getCard(cardv.getId());
            queue=queueDAO.getQueue(queuev.getId());
        } else {
            return;
        }
        if (queue.getCards().remove(card)) queueDAO.update(queue);;
    }
    private Queue queueFromView(QueueView queueView){
        Queue queue;
        if (queueView.getId() != null && queueView.getId() > 0) {
            queue = queueDAO.getQueue(queueView.getId());
        } else {
            queue = new Queue();
        }
        queue.setName(queueView.getName());
        //set cards list
        List<Card> cards=new ArrayList<>();
        for (CardView cv:queueView.getCards()){
            cards.add(cardFacade.cardFromView(cv));
        }
        queue.setCards(cards);
        return queue;
    }
}
