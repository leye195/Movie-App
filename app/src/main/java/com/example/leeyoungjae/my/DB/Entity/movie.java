package com.example.leeyoungjae.my.DB.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*

" date_val text, "+
            " user_rating float, "+
            " audience_rating float, "+
            " reviewer_rating float, "+
            " reservation_rate float, "+
            " reservation_grade integer, "+
            " grade integer, "+
            " thumb text, "+
            " image text, "+
            " photos text, "+
            " videos text, "+
            " outlink text, "+
            " genre text, "+
            " duration integer, "+
            " audience integer, "+
            " synopsis text, "+
            " director text, "+
            " actor text, "+
            " _like integer, "+
            " _dislike integer"+
 */
@Entity(tableName = "movie")
public class movie {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "date_val")
    private String date_val;
    @ColumnInfo(name = "user_rating")
    private float user_rating;
    @ColumnInfo(name = "audience_rating")
    private float audience_rating;
    @ColumnInfo(name = "reviewer_rating")
    private float reviewer_rating;
    @ColumnInfo(name = "reservation_rate")
    private float reservation_rate;
    @ColumnInfo(name = "reservation_grade")
    private int reservation_grade;
    @ColumnInfo(name = "grade")
    private int grade;
    @ColumnInfo(name = "thumb")
    private String thumb;
    @ColumnInfo(name = "image")
    private String image;
    @ColumnInfo(name = "photos")
    private String photos;
    @ColumnInfo(name = "videos")
    private String videos;
    @ColumnInfo(name = "outlink")
    private String outlink;
    @ColumnInfo(name = "genre")
    private String genre;
    @ColumnInfo(name = "duration")
    private int duration;
    @ColumnInfo(name = "audience")
    private int audience;
    @ColumnInfo(name = "synopsis")
    private String synopsis;
    @ColumnInfo(name = "director")
    private String director;
    @ColumnInfo(name = "actor")
    private String actor;
    @ColumnInfo(name = "_like")
    private int _like;
    @ColumnInfo(name = "_dislike")
    private int _dislike;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_val() {
        return date_val;
    }

    public void setDate_val(String date_val) {
        this.date_val = date_val;
    }

    public float getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(float user_rating) {
        this.user_rating = user_rating;
    }

    public float getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(float audience_rating) {
        this.audience_rating = audience_rating;
    }

    public float getReviewer_rating() {
        return reviewer_rating;
    }

    public void setReviewer_rating(float reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }

    public float getReservation_rate() {
        return reservation_rate;
    }

    public void setReservation_rate(float reservation_rate) {
        this.reservation_rate = reservation_rate;
    }

    public int getReservation_grade() {
        return reservation_grade;
    }

    public void setReservation_grade(int reservation_grade) {
        this.reservation_grade = reservation_grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getOutlink() {
        return outlink;
    }

    public void setOutlink(String outlink) {
        this.outlink = outlink;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int get_like() {
        return _like;
    }

    public void set_like(int _like) {
        this._like = _like;
    }

    public int get_dislike() {
        return _dislike;
    }

    public void set_dislike(int _dislike) {
        this._dislike = _dislike;
    }
}
