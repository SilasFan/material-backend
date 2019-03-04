package com.material.types;


public class Person {
    private String name;
    private String phone;
    private String group;
    private String studentNumber;

    public Person(String name, String phone, String group, String studentNumber) {
        this.name = name;
        this.phone = phone;
        this.group = group;
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGroup() {
        return group;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
