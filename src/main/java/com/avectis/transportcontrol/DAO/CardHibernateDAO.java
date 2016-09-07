/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.Card;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author DPoplauski
 */
public class CardHibernateDAO extends BaseHibernateDAO implements CardDAO{
     /**
     * add new Card entity using Hibernate
     * 
     * @param card Card 
     * @return created Card entity
     */
    @Override
    public Long addCard(Card card){
        Session session=sessionFactory.getCurrentSession();
        return (Long)session.save(card);
    }
     /**
     * unpade Card using Hibernate
     * 
     * @param card Card - witch entity to update
     */
    @Override
    public void Update(Card card){
        Session session=sessionFactory.getCurrentSession();
        session.update(card);
    }
    /**
     * get Car object from DB using Hibernate
     * 
     * @param id
     * @return Card object
     */
    @Override
    public Card getCard(Long id){
        Session session=sessionFactory.getCurrentSession();
        return (Card) session.load(Card.class, id);
    }
    /**
     * get all Cards from DB using Hibernate
     * 
     * @return List of Card objects
     */
    @Override
    public List<Card> getCards(){
        Session session=sessionFactory.getCurrentSession();
        Criteria criteria=session.createCriteria(Card.class);
        criteria.addOrder(Order.desc("createDate"));
        return (List<Card>)criteria.list();
    }
    /**
     * delete Card object from DB using Hibernate
     * 
     * @param card Card - card object to delete
     */
    @Override
    public void deleteCard(Card card){
        Session session=sessionFactory.getCurrentSession();
        session.delete(card);
    }
}
