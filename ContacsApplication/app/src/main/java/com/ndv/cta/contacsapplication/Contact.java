package com.ndv.cta.contacsapplication;

import java.io.Serializable;

/**
 * Created by admin on 11/7/2017.
 */

public class Contact implements Serializable{
    private String Name;
    private String Phone;
    private int Avatar;
    private boolean Sex;

    public Contact(String name, String phone, int avatar, boolean sex) {
        Name = name;
        Phone = phone;
        Avatar = avatar;
        Sex = sex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAvatar() {
        return Avatar;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean sex) {
        Sex = sex;
    }
}
