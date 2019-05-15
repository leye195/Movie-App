package com.example.leeyoungjae.my.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private String code;
    @SerializedName("resultType")
    private String resultType;
    @SerializedName("result")
    private ArrayList<MovieInfo>result;

    public MovieList(String message, String code, String resultType, ArrayList<MovieInfo> result) {
        this.message = message;
        this.code = code;
        this.resultType = resultType;
        this.result = result;
    }

    public MovieList(ArrayList<MovieInfo> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public ArrayList<MovieInfo> getResult() {
        return result;
    }

    public void setResult(ArrayList<MovieInfo> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MovieList{");
        sb.append("message='").append(message).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", resultType='").append(resultType).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
