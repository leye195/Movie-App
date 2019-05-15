package com.example.leeyoungjae.my.Model;

import com.google.gson.annotations.SerializedName;

public class MovieInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("title_eng")
    private String title_eng;
    @SerializedName("date")
    private String date;
    @SerializedName("user_rating")
    private String user_rating;
    @SerializedName("audience_rating")
    private String audience_rating;
    @SerializedName("reviewer_rating")
    private String reviewer_rating;
    @SerializedName("reservation_rate")
    private String reservation_rate;
    @SerializedName("reservation_grade")
    private String reservation_grade;
    @SerializedName("grade")
    private String grade;
    @SerializedName("thumb")
    private String thumb;
    @SerializedName("image")
    private String image;

    public MovieInfo(String id, String title, String title_eng, String date, String user_rating, String audience_rating, String reviewer_rating, String reservation_rate, String reservation_grade, String thumb, String image) {
        this.id = id;
        this.title = title;
        this.title_eng = title_eng;
        this.date = date;
        this.user_rating = user_rating;
        this.audience_rating = audience_rating;
        this.reviewer_rating = reviewer_rating;
        this.reservation_rate = reservation_rate;
        this.reservation_grade = reservation_grade;
        this.thumb = thumb;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_eng() {
        return title_eng;
    }

    public void setTitle_eng(String title_eng) {
        this.title_eng = title_eng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public String getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(String audience_rating) {
        this.audience_rating = audience_rating;
    }

    public String getReviewer_rating() {
        return reviewer_rating;
    }

    public void setReviewer_rating(String reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }

    public String getReservation_rate() {
        return reservation_rate;
    }

    public void setReservation_rate(String reservation_rate) {
        this.reservation_rate = reservation_rate;
    }

    public String getReservation_grade() {
        return reservation_grade;
    }

    public void setReservation_grade(String reservation_grade) {
        this.reservation_grade = reservation_grade;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
