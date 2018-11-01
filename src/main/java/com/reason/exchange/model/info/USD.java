package com.reason.exchange.model.info;

public class USD extends CurrencyType{

    private final static USD instance = new USD();
    private final static String NAME = "Доллар США";
    private final static String PHOTO = "united-states-of-america.svg";
    private final static String EQUALITY = "$";
    private final static String CARDNUMS = "6875";
    private final static String CODE = "USD";

    public USD() {
        super(NAME, PHOTO, EQUALITY,CARDNUMS,CODE);
    }

    public static USD getInstance() {
        return instance;
    }
    
}
