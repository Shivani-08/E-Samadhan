package com.example.sakshi.esamadhan;

/**
 * Created by sakshi on 3/20/2017.
 */

public class user {
    private String name;
    private String phone;
    private String password;
    private String email;
    private String bhamashahid;
    private String pincode;
    private String address;
    private String location;


    public String getAddress() {
        return address;
    }

    public String getBhamashahid() {
        return bhamashahid;
    }
    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getPincode() {
        return pincode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBhamashahid(String bhamashahid) {
        this.bhamashahid = bhamashahid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}




