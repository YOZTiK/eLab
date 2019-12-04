package com.example.elab.activities.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.elab.R;
import com.example.elab.database.UpdateTask;
import com.example.elab.database.UserDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    UserDatabase db;
    UserViewModel users;

    String user_id;
    String profile_image;
    String user_tag;
    String user_name;
    String last_name;
    String status;
    String user_type;
    String bachelor_degree;
    int ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView profileIV = findViewById(R.id.userProfileImage);
        TextView userNameTV = findViewById(R.id.userNameText);
        TextView lastNameTV = findViewById(R.id.lastNameText);
        TextView userTagTV = findViewById(R.id.userTagText);
        TextView userTypeTV = findViewById(R.id.userTypeText);
        TextView userStatusTV = findViewById(R.id.userStatusText);
        RatingBar ratingBar = findViewById(R.id.ratingBar);


        users = ViewModelProviders.of(this).get(UserViewModel.class);

        user_id = ""+getIntent().getExtras().getString("user_id");
        profile_image = getIntent().getExtras().getString("profile_image");
        user_tag = getIntent().getExtras().getString("user_tag");
        user_name = getIntent().getExtras().getString("user_name");
        last_name = getIntent().getExtras().getString("last_name");
        status = getIntent().getExtras().getString("status");
        user_type = getIntent().getExtras().getString("user_type");
        bachelor_degree = getIntent().getExtras().getString("bachelor_degree");
        ranking = Integer.parseInt(getIntent().getExtras().getString("ranking"));

        userNameTV.setText(user_name);
        lastNameTV.setText(last_name);
        userTagTV.setText("Tag: "+user_tag);
        userTypeTV.setText("Tipo: "+user_type);
        userStatusTV.setText("Status: "+status);
        Picasso.get()
                .load(profile_image)
                .into(profileIV);

        ratingBar.setRating(ranking);

    }

    public void modifyUser(View view) {
        /*Send the info to another screen to modify...*/
        Intent i = new Intent(this, ModifyUserActivity.class);

        i.putExtra("user_id", user_id);
        i.putExtra("profile_image", profile_image);
        i.putExtra("user_tag", user_tag);
        i.putExtra("user_name", user_name);
        i.putExtra("last_name", last_name);
        i.putExtra("status", status);
        i.putExtra("user_type", user_type);
        i.putExtra("bachelor_degree", bachelor_degree);
        i.putExtra("ranking", ranking);

        startActivity(i);
    }

}