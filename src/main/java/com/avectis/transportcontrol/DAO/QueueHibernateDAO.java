/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.Queue;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DPoplauski
 */
public class QueueHibernateDAO extends BaseHibernateDAO implements QueueDAO{
    /**
     * unpade entity using Hibernate
     * 
     * @param queue Queue - witch entity to update
     */
    @Override
    public void update(Queue queue){
        Session session=sessionFactory.getCurrentSession();
        session.update(queue);
    }
    /**
     * add new Queue entity using Hibernate
     * 
     * @return created Queue entity
     */
    @Override
    public Long addQueue(Queue queue){
        Session session=sessionFactory.getCurrentSession();
        return (Long)session.save(queue);
    }
    /**
     * get Queue object from DB using Hibernate
     * 
     * @param id Long - identifier of entity object
     * @return Queue object
     */
    @Override
    public Queue getQueue(Long id){
        Session session=sessionFactory.getCurrentSession();
        return (Queue)session.load(Queue.class, id );
        
    }
    /**
     * get Queue object from DB using Hibernate
     * 
     * @param name String - name of Queue
     * @return Queue object
     */
    @Override
    public List<Queue> getQueueByName(String name){
        Session session=sessionFactory.getCurrentSession();
        Criteria criteria=session.createCriteria(Queue.class);
        criteria.addOrder(Order.desc("createDate"));
        criteria.add(Restrictions.eq("name", name));
        return (List<Queue>)criteria.list();      
    }
    /**
     * get all Queue from Queue using Hibernate
     * 
     * @return List of Queue objects
     */
    @Override
    public List<Queue> getQueueList(){
        Session session=sessionFactory.getCurrentSession();
        Criteria criteria=session.createCriteria(Queue.class);
        return (List<Queue>)criteria.list();    
    }
    /**
     * delete Queue object from DB using Hibernate
     * 
     * @param queue Queue - Queue object to delete
     */
    @Override
    public void deleteQueue(Queue queue){
        Session session=sessionFactory.getCurrentSession();
        session.delete(queue);
    }
}
