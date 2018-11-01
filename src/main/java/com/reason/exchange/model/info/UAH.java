package com.reason.exchange.model.info;

public class UAH extends CurrencyType{
    
    private final static UAH instance = new UAH();
    private final static String NAME = "Украинская гривна";
    private final static String PHOTO = "ukraine.svg";
    private final static String EQUALITY = "грн";
    private final static String CARDNUMS = "5168";
    private final static String CODE = "UAH";

    public UAH() {
        super(NAME, PHOTO, EQUALITY,CARDNUMS,CODE);
    }

    public static UAH getInstance() {
        return instance;
    }
}
