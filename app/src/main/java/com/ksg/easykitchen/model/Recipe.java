package com.ksg.easykitchen.model;

public class Recipe {
    private String name;
    private String redirectUrl;
    private String imgUrl;

    public Recipe() {
    }

    public Recipe(String name, String redirectUrl, String imgUrl) {
        this.name = name;
        this.redirectUrl = redirectUrl;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
