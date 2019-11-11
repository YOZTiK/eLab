package com.example.elab.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="user_id")
    public String user_id;

    @ColumnInfo(name = "profile_image")
    public String profile_image;

    @ColumnInfo(name = "user_tag")
    public String user_tag;

    @ColumnInfo(name = "user_name")
    public String user_name;

    @ColumnInfo(name = "last_name")
    public String last_name;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "user_type")
    public String user_type;

    @ColumnInfo(name = "bachelor_degree")
    public String bachelor_degree;

    @ColumnInfo(name = "ranking")
    public int ranking;

    @Override
    public String toString(){
        return user_id + ", " +
        profile_image + ", " +
        user_tag + ", " +
        user_name + ", " +
        last_name + ", " +
        status + ", " +
        user_type + ", " +
        bachelor_degree + ", " +
        ranking;
    }


}
