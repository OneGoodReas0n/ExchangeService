package com.reason.exchange.service;

import com.reason.exchange.dao.ClientToCardsDAO;
import com.reason.exchange.model.ClientToCards;
import com.reason.exchange.model.CreditCard;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ClientToCardsService{

    @Autowired
    ClientToCardsDAO dao;
    
    @Autowired
    CreditCardService creditCardService;
    
    public boolean save(ClientToCards t) {
        try {
            dao.add(t);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean update(ClientToCards t) {
        try {
            dao.update(t);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean delete(ClientToCards t) {
        try {
            dao.deleteById(t.getId());
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public List<CreditCard> getCardIdsByClient(int id) {
        List<CreditCard> cards = new ArrayList<>();
        for (Integer cardId : dao.getOneClientCards(id)) {
            CreditCard card = creditCardService.getOne(cardId);
            cards.add(card);
        }
        return cards;
    }
    
    public ClientToCards getOneByCardId(int id){
        return dao.getOneByCardId(id);
    }
    
}
