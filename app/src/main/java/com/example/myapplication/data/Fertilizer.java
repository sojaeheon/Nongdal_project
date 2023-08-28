package com.example.myapplication.data;

public class Fertilizer {
    private int profile; // 프로필사진
    private String name; // 비료이름
    private String description; // 비료정보
    private String hyperlink;


    public int getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public String getHyperlink(){   return hyperlink;   }


    public Fertilizer(int profile, String name, String description, String hyperlink) {
        this.profile = profile;
        this.name = name;
        this.description = description;
        this.hyperlink = hyperlink;

    }


}
