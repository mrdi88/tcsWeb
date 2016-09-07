/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.Queue;
import java.util.List;

/**
 *
 * @author DPoplauski
 */
public interface QueueDAO {
    public void update(Queue queue);
    public Long addQueue(Queue queue);
    public Queue getQueue(Long id);
    public List<Queue> getQueueByName(String name);
    public List<Queue> getQueueList();
    public void deleteQueue(Queue queue);
}
