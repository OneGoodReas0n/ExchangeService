package com.reason.exchange.service;

import com.reason.exchange.dao.CreditCardDAO;
import com.reason.exchange.model.CreditCard;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService extends AbstractService<CreditCard>{

    @Autowired
    CreditCardDAO dao;
    
    @Override
    public CreditCard save(CreditCard t) {
        try {
            return dao.add(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CreditCard getOne(int id) {
        return dao.getOneById(id);
    }

    @Override
    public boolean update(CreditCard t) {
        try {
            dao.update(t);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(CreditCard t) {
        try {
            dao.deleteById(t.getId());
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public List<CreditCard> getAll() {
        return dao.getAll();
    }
    
    public CreditCard getOneByNumber(String number){
        return dao.getOneByNumber(number);
    }
    
}
