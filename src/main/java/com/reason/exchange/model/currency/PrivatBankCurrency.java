package com.reason.exchange.model.currency;

import com.reason.exchange.model.info.EUR;
import com.reason.exchange.model.info.CurrencyType;
import com.reason.exchange.model.info.RUB;
import com.reason.exchange.model.info.USD;

public class PrivatBankCurrency {
    
    private String currency_name;
    private String currency_baseName;
    private double buy;
    private double sell;
    private CurrencyType info;

    public PrivatBankCurrency() {
    }

    public PrivatBankCurrency(String currency_name, String currency_baseName, double buy, double sell) {
        this.currency_name = currency_name;
        this.currency_baseName = currency_baseName;
        this.buy = buy;
        this.sell = sell;
        setInfo();
    }

    public CurrencyType getInfo() {
        return info;
    }

    public void setInfo() {
        if (this.currency_name.equals("USD")) {
            this.info = USD.getInstance();
        }
        if (this.currency_name.equals("EUR")) {
            this.info = EUR.getInstance();
        }
        if (this.currency_name.equals("RUB") || this.currency_name.equals("RUR")) {
            this.info = RUB.getInstance();
        }
    }
    
    
    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getCurrency_baseName() {
        return currency_baseName;
    }

    public void setCurrency_baseName(String currency_baseName) {
        this.currency_baseName = currency_baseName;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
    

    @Override
    public String toString() {
        return "Ccy: "+this.currency_name + " Base_Ccy: "+this.currency_baseName + " Buy: "+this.buy + " Sell: "+this.sell;
    }
    
    
}
