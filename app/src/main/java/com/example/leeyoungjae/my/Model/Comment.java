package com.example.leeyoungjae.my.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Comment implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("writer")
    private String writer;
    @SerializedName("movieId")
    private String movie_id;
    @SerializedName("time")
    private String time;
    @SerializedName("contents")
    private String comment;
    @SerializedName("rating")
    private float rate;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("writer_image")
    private String writer_img;
    @SerializedName("recommend")
    private int recommend;


    public Comment(Parcel src){
        id=src.readString();
        writer=src.readString();
        movie_id=src.readString();
        time=src.readString();
        comment=src.readString();
        rate=src.readFloat();
        timestamp=src.readString();
        writer_img=src.readString();
        recommend=src.readInt();
    }

    public Comment(String id, String writer, String movie_id, String time, String comment, float rate, String timestamp, String writer_img, int recommend) {
        this.id = id;
        this.writer = writer;
        this.movie_id = movie_id;
        this.time = time;
        this.comment = comment;
        this.rate = rate;
        this.timestamp = timestamp;
        this.writer_img = writer_img;
        this.recommend = recommend;
    }

    public Comment(String id, String writer, String movie_id, String time, String comment, float rate) {
        this.id = id;
        this.writer = writer;
        this.movie_id = movie_id;
        this.time = time;
        this.comment = comment;
        this.rate = rate;
    }

    public Comment(String id, String time, String comment, float rate) {
        this.writer = id;
        this.time = time;
        this.comment = comment;
        this.rate=rate;
    }
    public static final Parcelable.Creator CREATOR=new Parcelable.Creator(){

        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getWriter_img() {
        return writer_img;
    }

    public void setWriter_img(String writer_img) {
        this.writer_img = writer_img;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(writer);
        dest.writeString(movie_id);
        dest.writeString(comment);
        dest.writeString(time);
        dest.writeFloat(rate);
        dest.writeString(timestamp);
        dest.writeString(writer_img);
        dest.writeInt(recommend);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Comment{");
        sb.append("id='").append(id).append('\'');
        sb.append(", writer='").append(writer).append('\'');
        sb.append(", movie_id='").append(movie_id).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", rate=").append(rate);
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append(", writer_img='").append(writer_img).append('\'');
        sb.append(", recommend=").append(recommend);
        sb.append('}');
        return sb.toString();
    }
}

