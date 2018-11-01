package com.reason.exchange.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.Calendar;


public class Order {
    
    private int id;
    private double sum;
    private int fromCardId;
    private String fromCardEquality;
    private int toCardId;
    private String toCardEquality;
    private double moneyAmount;
    private double curs;
    private int status;
    private int client_id;
    private Date date;

    public Order() {
    }

    public Order(double sum, int fromCardId, int toCardId,String fromCardEquality, 
            String toCardEquality, double moneyAmount, double curs, int client_id) {
        this.sum = sum;
        this.fromCardId = fromCardId;
        this.toCardId = toCardId;
        this.moneyAmount = moneyAmount;
        this.curs = curs;
        this.client_id = client_id;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
        this.fromCardEquality = fromCardEquality;
        this.toCardEquality = toCardEquality;
    }
    
    public Order(double sum, int fromCardId, int toCardId, double moneyAmount, double curs) {
        this.sum = sum;
        this.fromCardId = fromCardId;
        this.toCardId = toCardId;
        this.moneyAmount = moneyAmount;
        this.curs = curs;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
    }

    public Order(int id, double sum, int fromCardId, String fromCardEquality, 
            int toCardId, String toCardEquality, double moneyAmount, double curs, 
            int status, int client_id, Date date) {
        this.id = id;
        this.sum = sum;
        this.fromCardId = fromCardId;
        this.fromCardEquality = fromCardEquality;
        this.toCardId = toCardId;
        this.toCardEquality = toCardEquality;
        this.moneyAmount = moneyAmount;
        this.curs = curs;
        this.status = status;
        this.client_id = client_id;
        this.date = date;
    }

    public String getFromCardEquality() {
        return fromCardEquality;
    }

    public void setFromCardEquality(String fromCardEquality) {
        this.fromCardEquality = fromCardEquality;
    }

    public String getToCardEquality() {
        return toCardEquality;
    }

    public void setToCardEquality(String toCardEquality) {
        this.toCardEquality = toCardEquality;
    }

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
    
    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

    public int getFromCardId() {
        return fromCardId;
    }

    public void setFromCardId(int fromCardId) {
        this.fromCardId = fromCardId;
    }

    public int getToCardId() {
        return toCardId;
    }

    public void setToCardId(int toCardId) {
        this.toCardId = toCardId;
    }
    
    public String getSumFormat(){
        return String.format("%.2f", getSum());
    }
    
    public String getCursFormat(){
        return String.format("%.2f", getCurs());
    }
    
    public String getMoneyAmountFormat(){
        return String.format("%.2f", getMoneyAmount());
    }

    public double getCurs() {
        return round(curs, 2);
    }

    public void setCurs(double curs) {
        this.curs = curs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSum() {
        return round(sum,2);
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getMoneyAmount() {
        return round(moneyAmount,2);
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
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
