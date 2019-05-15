package com.example.leeyoungjae.my.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String userId;
    private String userpw;
    @SerializedName("validate")
    private boolean validate;
    public User(String id,String password,boolean validate){
        this.userId=id;
        this.userpw=password;
        this.validate=validate;
    }
    public User(String id,String password){
        this.userId=id;
        this.userpw=password;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

    public String getPassword() {
        return userpw;
    }

    public void setPassword(String password) {
        this.userpw = password;
    }
    public boolean isValidate() {
        return validate;
    }
    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}
