/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avectis.transportcontrol.DAO;

import com.avectis.transportcontrol.entity.Card;
import java.util.List;

/**
 *
 * @author DPoplauski
 */
public interface CardDAO {
    public Long addCard(Card card);
    public void Update(Card card);
    public Card getCard(Long id);
    public List<Card> getCards();
    public void deleteCard(Card card);
}
