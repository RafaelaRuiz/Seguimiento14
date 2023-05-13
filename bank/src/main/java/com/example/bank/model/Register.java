package com.example.bank.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register implements Comparable<Register>{
    private Date date;
    private Double amount;
    private String type;
    private String desc;


    public Register() {
    }


    public Register(Double amount, String type, String desc) {
        this.amount = amount;
        this.type = type;
        this.desc = desc;
        this.date = null;
    }

    public void convertStringToDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        this.date = dateFormat.parse(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int compareTo(Register o) {
        return this.date.compareTo(o.date);
    }
}
