package com.reason.exchange.model.currency;

import com.reason.exchange.model.info.EUR;
import com.reason.exchange.model.info.CurrencyType;
import com.reason.exchange.model.info.RUB;
import com.reason.exchange.model.info.USD;

public class NBUCurrency {
    
    private long id;
    private String name;
    private double rate;
    private String cc;
    private String exchangeDate;
    private CurrencyType info;
   
    public NBUCurrency() {
    }

    public NBUCurrency(long id, String name, double rate, String cc, String date) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.cc = cc;
        this.exchangeDate = date;
        setInfo();
    }

    public CurrencyType getInfo() {
        return info;
    }

    public void setInfo() {
        if (this.cc.equals("USD")) {
            this.info = USD.getInstance();
        }
        if (this.cc.equals("EUR")) {
            this.info = EUR.getInstance();
        }
        if (this.cc.equals("RUB") || this.cc.equals("RUR")) {
            this.info = RUB.getInstance();
        }
    }
    
    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Id: "+this.id + " name: "+this.name + " rate: "+this.rate + " cc: " +this.cc + " date: " +this.exchangeDate; 
    }
    
    
}
