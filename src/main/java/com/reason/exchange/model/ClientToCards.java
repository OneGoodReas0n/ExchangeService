package com.reason.exchange.model;

public class ClientToCards {
    
    int id;
    int client_id;
    int card_id;

    public ClientToCards() {
    }

    public ClientToCards(int client_id, int card_id) {
        this.client_id = client_id;
        this.card_id = card_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }
    
    
}
