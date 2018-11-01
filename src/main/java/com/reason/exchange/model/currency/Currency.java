package com.reason.exchange.model.currency;

import com.reason.exchange.model.info.EUR;
import com.reason.exchange.model.info.CurrencyType;
import com.reason.exchange.model.info.RUB;
import com.reason.exchange.model.info.USD;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Currency {

    private int id;
    private String cur_name;
    private double buyPrivat;
    private double sellPrivat;
    private double buyNBU;
    private double sellNBU;
    private CurrencyType info;

    public Currency() {
    }

    public Currency(String cur_name, double buyPrivat, double sellPrivat, double buyNBU, double sellNBU) {
        this.cur_name = cur_name;
        this.buyPrivat = buyPrivat;
        this.sellPrivat = sellPrivat;
        this.buyNBU = buyNBU;
        this.sellNBU = sellNBU;
        setInfo();
    }
    
    public Currency(int id, String cur_name, double buyPrivat, double sellPrivat, double buyNBU) {
        this.id =id;
        this.cur_name = cur_name;
        this.buyPrivat = buyPrivat;
        this.sellPrivat = sellPrivat;
        this.buyNBU = buyNBU;
        setInfo();
    }
    
    public Currency(String cur_name, double buyPrivat, double sellPrivat, double buyNBU) {
        this.cur_name = cur_name;
        this.buyPrivat = buyPrivat;
        this.sellPrivat = sellPrivat;
        this.buyNBU = buyNBU;
        setInfo();
    }

    public String getCur_name() {
        return cur_name;
    }

    public void setCur_name(String cur_name) {
        this.cur_name = cur_name;
    }

    public CurrencyType getInfo() {
        return info;
    }

    public void setInfo() {
        if (cur_name.equals("USD")) {
            this.info = USD.getInstance();
        }
        if (cur_name.equals("EUR")) {
            this.info = EUR.getInstance();
        }
        if (cur_name.equals("RUB") || cur_name.equals("RUR")) {
            this.info = RUB.getInstance();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBuyPrivat() {
        return round(buyPrivat,2);
    }

    public void setBuyPrivat(double buyPrivat) {
        this.buyPrivat = buyPrivat;
    }

    public double getSellPrivat() {
        return round(sellPrivat, 2);
    }
    

    public void setSellPrivat(double sellPrivat) {
        this.sellPrivat = sellPrivat;
    }

    public double getBuyNBU() {
        return round(buyNBU, 2);
    }
    
    public String getBuyPrivatFormat() {
        return String.format("%.2f", getBuyPrivat());
    }

    public String getSellPrivatFormat() {
        return String.format("%.2f", getSellPrivat());
    }

    public String getBuyNBUFormat() {
        return String.format("%.2f", getBuyNBU());
    }

    public void setBuyNBU(double buyNBU) {
        this.buyNBU = buyNBU;
    }

    public double getSellNBU() {
        return sellNBU;
    }

    public void setSellNBU(double sellNBU) {
        this.sellNBU = sellNBU;
    }

    @Override
    public String toString() {
        return "Name: " + this.getCur_name() + " PrivatBuy: " + this.buyPrivat + " PrivatSell: " + this.sellPrivat + " NBUBuy: " + this.buyNBU;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
