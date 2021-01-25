package com.example.hololiveapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class VideoDataModel {

    @JsonProperty("youtube_id")
    private String youtube_id;
    @JsonProperty("thumbnail")
    private String thumbnail;

    public VideoDataModel() {

    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getYoutube_id() {
        return youtube_id;
    }

    public void setYoutube_id(String youtube_id) {
        this.youtube_id = youtube_id;
    }
}
