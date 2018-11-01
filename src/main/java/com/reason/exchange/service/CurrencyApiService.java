package com.reason.exchange.service;

import com.reason.exchange.logic.Parser;
import com.reason.exchange.model.currency.Currency;
import com.reason.exchange.model.currency.NBUCurrency;
import com.reason.exchange.model.currency.PrivatBankCurrency;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyApiService {
    
    @Autowired
    private Parser parser;
    
    public List<PrivatBankCurrency> getPrivatCources(){
        return parser.getPrivatCurrency();
    }
    
    public List<NBUCurrency> getNBUCources(){
        return parser.getNBUCurrency();
    }
    
    public List<Currency> getCurrencyList(){
        return parser.getCurrency();
    }
}
