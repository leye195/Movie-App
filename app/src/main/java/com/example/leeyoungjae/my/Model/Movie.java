package com.example.leeyoungjae.my.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName("lastBuildDate")
    private String lastBuildDate;
    @SerializedName("total")
    private int total;
    @SerializedName("start")
    private int start;
    @SerializedName("display")
    private int display;
    @SerializedName("items")
    private List<itemInfo> items;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<itemInfo> getItems() {
        return items;
    }

    public void setItems(List<itemInfo> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("lastBuildDate='").append(lastBuildDate).append('\'');
        sb.append(", total=").append(total);
        sb.append(", start=").append(start);
        sb.append(", display=").append(display);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }

    public static class itemInfo{
        @SerializedName("title")
        private String titie;
        @SerializedName("link")
        private String link;
        @SerializedName("subtitle")
        private String subtitle;
        @SerializedName("pubdate")
        private String pubDate;
        @SerializedName("director")
        private String director;
        @SerializedName("actor")
        private String actor;
        @SerializedName("image")
        private String image;
        @SerializedName("userRating")
        private double userRating;

        public String getTitie() {
            return titie;
        }

        public void setTitie(String titie) {
            this.titie = titie;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getUserRating() {
            return userRating;
        }

        public void setUserRating(double userRating) {
            this.userRating = userRating;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("itemInfo{");
            sb.append("titie='").append(titie).append('\'');
            sb.append(", link='").append(link).append('\'');
            sb.append(", subtitle='").append(subtitle).append('\'');
            sb.append(", pubDate='").append(pubDate).append('\'');
            sb.append(", director='").append(director).append('\'');
            sb.append(", actor='").append(actor).append('\'');
            sb.append(", image='").append(image).append('\'');
            sb.append(", userRating=").append(userRating);
            sb.append('}');
            return sb.toString();
        }
    }

}
