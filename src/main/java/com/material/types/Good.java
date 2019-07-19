package com.material.types;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Good")
public class Good {
    private Integer id;
    private String name;
    private Integer amount;
    private Integer borrow;
    private List<String> brokenDes;
    private String error;

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public Good(String error) {
        this.error = error;
    }

    @PersistenceConstructor
    public Good( Integer id,String name, Integer amount, Integer borrow, List<String> brokenDes) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.borrow = borrow;
        this.brokenDes = brokenDes;
    }

    public Good(String name, Integer amount, Integer borrow, List<String> brokenDes) {
        this.name = name;
        this.amount = amount;
        this.borrow = borrow;
        this.brokenDes = brokenDes;
    }

    public Good(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }



    public Good(Integer id, Integer borrow) {
        this.id = id;
        this.borrow = borrow;
    }

    public Good(Integer id, Integer borrow, List<String> brokenDes) {
        this.id = id;
        this.borrow = borrow;
        this.brokenDes = brokenDes;
    }

    public Good(){

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getBorrow() {
        return borrow;
    }

    public List<String> getBrokenDes() {
        return brokenDes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setBorrow(Integer borrow) {
        this.borrow = borrow;
    }

    public void setBrokenDes(List<String> brokenDes) {
        this.brokenDes = brokenDes;
    }
}
