package com.metbetestimate.betestimate.models;

import java.sql.Timestamp;

//database model for posts
public class BetModel {

    public BetModel(){
        super();
    }
    
    public BetModel(String title, String content, String image_url){
        this.title = title;
        this.content = content;
        this.image_url = image_url;
    }

    private int id;
    private String title;
    private String content;
    private String image_url;
    private String team1;
    private String team2;
    private Timestamp timestamp;

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }    

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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
}
