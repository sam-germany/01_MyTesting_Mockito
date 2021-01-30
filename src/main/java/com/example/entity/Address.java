package com.example.entity;

import javax.persistence.Entity;

@Entity
public class Address {

    int houseNo;
    String street;

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressDetails() {
        System.out.println("------------333333333");
        return  houseNo + "_" + street;

    }
}
