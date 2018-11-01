package com.reason.exchange.model.info;

public class EUR extends CurrencyType{
    
    private final static EUR instance = new EUR();
    private final static String NAME = "Евро";
    private final static String PHOTO = "european-union.svg";
    private final static String EQUALITY = "€";
    private final static String CARDNUMS = "3284";
    private final static String CODE = "EUR";

    public EUR() {
        super(NAME, PHOTO, EQUALITY, CARDNUMS,CODE);
    }

    public static EUR getInstance() {
        return instance;
    }
    
    
}
