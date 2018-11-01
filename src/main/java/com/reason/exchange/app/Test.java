package com.reason.exchange.app;

import com.reason.exchange.business.ExchangeProcess;
import com.reason.exchange.dao.ClientDAO;
import com.reason.exchange.dao.CreditCardDAO;
import com.reason.exchange.logic.Parser;
import com.reason.exchange.model.Client;
import com.reason.exchange.model.CreditCard;
import com.reason.exchange.model.currency.Currency;
import com.reason.exchange.model.currency.NBUCurrency;
import com.reason.exchange.model.currency.PrivatBankCurrency;
import com.reason.exchange.service.CreditCardService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {

    public static void main(String[] args) {
        
        CreditCardService service = new CreditCardService();
        CreditCard card = new CreditCard("Рублевая");
        System.out.println(""+service.save(card));
        
        
    }
}
