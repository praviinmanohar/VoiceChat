package com.hueint.voicechat.Models;

public class User {

    String username;
    String imageUrl;
    String id;
    String text_status;

    public User(String username, String imageUrl, String id, String text_status) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.id = id;
        this.text_status = text_status;
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

    public String getText_status() {
        return text_status;
    }

    public void setText_status(String text_status) {
        this.text_status = text_status;
    }

    /* public User(String username, String imageUrl, String id) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public User(){

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
    }*/
}
