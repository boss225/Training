package com.ndv.fa.fragorientaapplication;

import java.io.Serializable;

/**
 * Created by admin on 11/15/2017.
 */

public class Student implements Serializable {
    private String Name;
    private int Birthday;
    private String Address;
    private String Email;

    public Student(String name, int birthday, String address, String email) {
        Name = name;
        Birthday = birthday;
        Address = address;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBirthday() {
        return Birthday;
    }

    public void setBirthday(int birthday) {
        Birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
