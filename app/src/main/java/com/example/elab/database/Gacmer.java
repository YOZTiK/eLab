package com.example.elab.database;

public class Gacmer {

    public String user_id;
    public String profile_image;
    public String user_tag;
    public String name;
    public String last_name;
    public String status;
    public String user_type;
    public String bachelor_degree;
    public int ranking;

    public Gacmer(){

    }

    public Gacmer(String user_id, String profile_image, String user_tag, String name, String last_name, String status, String user_type, String bachelor_degree, int ranking){
        this.user_id = user_id;
        this.profile_image = profile_image;
        this.user_tag = user_tag;
        this.name = name;
        this.last_name = last_name;
        this.status = status;
        this.user_type = user_type;
        this.bachelor_degree = bachelor_degree;
        this.ranking = ranking;
    }
}
