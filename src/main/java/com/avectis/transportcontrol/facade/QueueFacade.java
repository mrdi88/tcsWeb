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
import com.avectis.transportcontrol.entity.QueueType;
import com.avectis.transportcontrol.view.CardView;
import com.avectis.transportcontrol.view.QueueNameView;
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
        Queue queue=queueDAO.getQueue(id);
        if (queue!=null) return new QueueView(queue);
        else return null;
    }
    @Transactional(readOnly = true)
    public QueueView getQueueByName(String name){
        QueueView qv=null;
        List<Queue> qList = queueDAO.getQueueList();
        for (Queue q:qList){
            if (q.getName().equals(name)){
                qv= new QueueView(q);
                break;
            }
        }
        return qv;
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
    @Transactional(readOnly = true)
    public List<QueueView> getQueueList(QueueType type){
        List<QueueView> qvList=new ArrayList<>();
        List<Queue> qList = queueDAO.getQueueList();
        for (Queue q:qList){
            if (q.getType()==type) qvList.add(new QueueView(q));
        }
        return qvList;
    }
    @Transactional(readOnly = true)
    public List<QueueNameView> getQueueNameList(){
        List<QueueNameView> qnvList=new ArrayList<>();
        List<Queue> qList = queueDAO.getQueueList();
        for (Queue q:qList){
            qnvList.add(new QueueNameView(q));
        }
        return qnvList;
    }
    @Transactional(readOnly = true)
    public List<QueueNameView> getQueueNameList(QueueType type){
        List<QueueNameView> qnvList=new ArrayList<>();
        List<Queue> qList = queueDAO.getQueueList();
        for (Queue q:qList){
            if (q.getType()==type) qnvList.add(new QueueNameView(q));
        }
        return qnvList;
    }
    @Transactional
    public void moveCard(CardView cardv, QueueView from, QueueView to){
        Card card;
        if (cardv.getId() != null && cardv.getId() > 0) {
            card=cardDAO.getCard(cardv.getId());
        } else {
            return;
        }
        Queue f;
        if (from.getId() != null && from.getId() > 0) {
            f=queueDAO.getQueue(from.getId());
        } else {
            return;
        }
        Queue t;
        if (to.getId() != null && to.getId() > 0) {
            t=queueDAO.getQueue(to.getId());
        } else {
            return;
        }
        //remove
        f.getCards().remove(card);
        t.getCards().add(card);
        queueDAO.update(f);
        queueDAO.update(t);
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
        queue.setType(queueView.getType());
        //set cards list
        List<Card> cards=new ArrayList<>();
        for (CardView cv:queueView.getCards()){
            cards.add(cardFacade.cardFromView(cv));
        }
        queue.setCards(cards);
        return queue;
    }
}
