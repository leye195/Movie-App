package com.example.leeyoungjae.my.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentList {
    @SerializedName("result")
    private ArrayList<Comment>result;

    public CommentList(ArrayList<Comment> result) {
        this.result = result;
    }

    public ArrayList<Comment> getResult() {
        return result;
    }

    public void setResult(ArrayList<Comment> result) {
        this.result = result;
    }
}
