package com.reason.exchange.model;

import com.reason.exchange.model.info.CurrencyType;
import com.reason.exchange.model.info.EUR;
import com.reason.exchange.model.info.RUB;
import com.reason.exchange.model.info.UAH;
import com.reason.exchange.model.info.USD;

public class CreditCard {
    
    private int id;
    private String cardNumber;
    private String name;
    private double amount;
    private CurrencyType type;
    private int currencyTypeInt;

    public CreditCard() {
    }

    public CreditCard(String name) {
        this.name = name;
        setType(name);
        createCardNumber();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCurrencyTypeInt() {
        return currencyTypeInt;
    }

    public void setCurrencyTypeInt(int currencyTypeInt) {
        this.currencyTypeInt = currencyTypeInt;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String number) {
        this.cardNumber = number;
    }
    
    private void createCardNumber(){
        this.cardNumber =  this.type.getCardNums()+" "+getRandNum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyType getType() {
        CurrencyType type = new CurrencyType();
        if(this.name.equalsIgnoreCase("долларовая") || name.equalsIgnoreCase("usd")){
            type = USD.getInstance();
        }
        if(this.name.equals("евро") || name.equalsIgnoreCase("eur")){
            type = EUR.getInstance();
        }
        if(this.name.equals("рублевая") || name.equalsIgnoreCase("rub") || name.equalsIgnoreCase("rur")){
            type = RUB.getInstance();
        }
        if(this.name.equals("гривневая") || name.equalsIgnoreCase("uah") ){
            type = UAH.getInstance();
        }
        return type;
    }

    public void setType(String name) {
        if(name.equalsIgnoreCase("долларовая") || name.equalsIgnoreCase("usd")){
            this.type = USD.getInstance();
        }
        if(name.equals("евро") || name.equalsIgnoreCase("eur")){
            this.type = EUR.getInstance();
        }
        if(name.equals("рублевая") || name.equalsIgnoreCase("rub") || name.equalsIgnoreCase("rur")){
            this.type = RUB.getInstance();
        }
        if(name.equals("гривневая") || name.equalsIgnoreCase("uah") ){
            this.type = UAH.getInstance();
        }
    }
    
    private int getRandNum(){
        return 1000 + (int) (Math.random()*9000);
    }

    @Override
    public String toString() {
        return "Id: "+ getId()+" name: "+ getName()+ " card number: "+ getCardNumber()+" card amount: "+ getAmount();
    }
    
    
}
