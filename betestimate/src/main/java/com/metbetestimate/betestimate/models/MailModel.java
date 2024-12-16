package com.metbetestimate.betestimate.models;

import java.sql.Timestamp;

public class MailModel {
    private int id;
    private String email;
    private String content;
    private Timestamp timestamp;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }
    
    public String getContent(){
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
