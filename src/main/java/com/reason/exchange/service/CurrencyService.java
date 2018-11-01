package com.reason.exchange.service;

import com.reason.exchange.dao.CurrencyDAO;
import com.reason.exchange.model.currency.Currency;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService extends AbstractService<Currency>{

    @Autowired
    CurrencyDAO dao;
    
    @Override
    public Currency save(Currency t) {
        try {
            return dao.add(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Currency getOne(int id) {
        return dao.getOneById(id);
    }

    @Override
    public boolean update(Currency t) {
        try {
            dao.update(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Currency t) {
        try {
            dao.deleteById(t.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Currency> getAll() {
        return dao.getAll();
    }
    
    public Currency getOneByCode(String code){
        Currency currency = null;
        for (Currency obj : getAll()) {
            if(obj.getInfo().getCode().equals(code)){
                currency = obj;
            }
        }
        return currency;
    }
    
}
