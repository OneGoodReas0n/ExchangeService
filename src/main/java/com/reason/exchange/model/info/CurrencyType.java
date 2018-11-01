package com.reason.exchange.model.info;

public class CurrencyType {
    
    private int id;
    private String photo;
    private String name;
    private String equality;
    private String cardNums;
    private String code;

    public CurrencyType() {
    }

    public CurrencyType(String name, String photo, String equality, String cardNums,String code) {
        this.name = name;
        this.photo = photo;
        this.equality = equality;
        this.cardNums = cardNums;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardNums() {
        return cardNums;
    }

    public void setCardNums(String cardNums) {
        this.cardNums = cardNums;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquality() {
        return equality;
    }

    public void setEquality(String equality) {
        this.equality = equality;
    }
    
    
    

}
