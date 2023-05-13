package com.example.bank.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class RegisterList {
    ObservableList<Register> data = FXCollections.observableArrayList();


    public RegisterList() {
    }

    private static RegisterList instance=null;

    public static RegisterList getInstance(){
        if (instance==null) instance= new RegisterList();
        return  instance;
    }


    public ObservableList<Register> getData() {
        Collections.sort(data);
        return data;
    }

    public void setData(ObservableList<Register> nData) {
        this.data = nData;
    }
}
