package com.reason.exchange.info;

public class StaticInfo {
    
    private final static String PRIVATPATH = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private final static String NBUPATH = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public static String getPRIVATPATH() {
        return PRIVATPATH;
    }

    public static String getNBUPATH() {
        return NBUPATH;
    }
    
    
}
