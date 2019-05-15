package com.example.leeyoungjae.my.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieDetail {
    @SerializedName("result")
    private ArrayList<MovieItem>result;

    public ArrayList<MovieItem> getResult() {
        return result;
    }

    public void setResult(ArrayList<MovieItem> result) {
        this.result = result;
    }

    public static class MovieItem{
        @SerializedName("title")
        private String title;
        @SerializedName("id")
        private String id;
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
        @SerializedName("photos")
        private String photos;
        @SerializedName("videos")
        private String videos;
        @SerializedName("outlink")
        private String outlink;
        @SerializedName("genre")
        private String genre;
        @SerializedName("duration")
        private String duration;
        @SerializedName("audience")
        private String audience;
        @SerializedName("synopsis")
        private String synopsis;
        @SerializedName("like")
        private int like;
        @SerializedName("director")
        private String director;
        @SerializedName("actor")
        private String actor;
        @SerializedName("dislike")
        private int dislike;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
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

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getAudience() {
            return audience;
        }

        public void setAudience(String audience) {
            this.audience = audience;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
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

        public int getDislike() {
            return dislike;
        }

        public void setDislike(int dislike) {
            this.dislike = dislike;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("MovieItem{");
            sb.append("title='").append(title).append('\'');
            sb.append(", id='").append(id).append('\'');
            sb.append(", date='").append(date).append('\'');
            sb.append(", user_rating='").append(user_rating).append('\'');
            sb.append(", audience_rating='").append(audience_rating).append('\'');
            sb.append(", reviewer_rating='").append(reviewer_rating).append('\'');
            sb.append(", reservation_rate='").append(reservation_rate).append('\'');
            sb.append(", reservation_grade='").append(reservation_grade).append('\'');
            sb.append(", grade='").append(grade).append('\'');
            sb.append(", thumb='").append(thumb).append('\'');
            sb.append(", image='").append(image).append('\'');
            sb.append(", photos='").append(photos).append('\'');
            sb.append(", videos='").append(videos).append('\'');
            sb.append(", outlink='").append(outlink).append('\'');
            sb.append(", genre='").append(genre).append('\'');
            sb.append(", duration='").append(duration).append('\'');
            sb.append(", audience='").append(audience).append('\'');
            sb.append(", synopsis='").append(synopsis).append('\'');
            sb.append(", like='").append(like).append('\'');
            sb.append(", director='").append(director).append('\'');
            sb.append(", actor='").append(actor).append('\'');
            sb.append(", dislike='").append(dislike).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
