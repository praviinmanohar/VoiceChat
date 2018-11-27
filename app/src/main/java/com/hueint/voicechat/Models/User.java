package com.hueint.voicechat.Models;

public class User {

    String username;
    String imageUrl;
    String id;

    public User(String username, String imageUrl, String id) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
