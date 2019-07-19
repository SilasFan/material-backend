package com.material.types;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Record")
public class Record {
    @Id
    private String id;
    private Person res_person;
    private List<Good> items;
    private Boolean isReturn;
    private String note;
    private String date;

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public Record(String error) {
        this.error = error;
    }

    private String error;



    @PersistenceConstructor
    public Record(String id, Person res_person, List<Good> items, Boolean isReturn, String note, String date) {
        this.id = id;
        this.res_person = res_person;
        this.items = items;
        this.isReturn = isReturn;
        this.note = note;
        this.date = date;
    }

    @PersistenceConstructor
    public Record(Person res_person, List<Good> items) {
        this.res_person = res_person;
        this.items = items;
        this.isReturn = false;
    }

    public Record() {
    }

    public Record(String id, String note) {
        this.id = id;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public Person getRes_person() {
        return res_person;
    }

    public List<Good> getItems() {
        return items;
    }

    public Boolean getReturn() {
        return isReturn;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRes_person(Person res_person) {
        this.res_person = res_person;
    }

    public void setItems(List<Good> items) {
        this.items = items;
    }

    public void setReturn(Boolean aReturn) {
        isReturn = aReturn;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
