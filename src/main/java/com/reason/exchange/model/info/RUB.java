package com.reason.exchange.model.info;

public class RUB extends CurrencyType{
    
    private final static RUB instance = new RUB();
    private final static String NAME = "Российский рубль";
    private final static String PHOTO = "russia.svg";
    private final static String EQUALITY = "руб";
    private final static String CARDNUMS = "1244";
    private final static String CODE = "RUB";

    public RUB() {
        super(NAME, PHOTO, EQUALITY,CARDNUMS,CODE);
    }

    public static RUB getInstance() {
        return instance;
    }
    
    
}
