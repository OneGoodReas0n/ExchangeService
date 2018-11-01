package com.reason.exchange.model;

import java.util.Date;

public class ExchangeJournal {
    
    private int id;
    private Date date;
    private Order order;

    public ExchangeJournal() {
    }

    public ExchangeJournal(Date date, Order order) {
        this.date = date;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    
}
