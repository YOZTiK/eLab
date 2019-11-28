package com.example.elab.activities.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.elab.R;
import com.example.elab.database.UpdateTask;
import com.example.elab.database.UserDatabase;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    UserDatabase db;
    UserViewModel users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView coName = findViewById(R.id.companyName);
        TextView coDescription = findViewById(R.id.companyDescription);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        users = ViewModelProviders.of(this).get(UserViewModel.class);

        final String user_id = ""+getIntent().getExtras().getString("user_id");
        String user_name = getIntent().getExtras().getString("user_name");
        String user_tag = getIntent().getExtras().getString("user_tag");
        String profile_image = getIntent().getExtras().getString("profile_image");
        final int ranking = Integer.parseInt(getIntent().getExtras().getString("ranking"));

        Log.d("RATING", ranking+"");

        coName.setText(user_name+"");
        coDescription.setText(user_tag+"");

        //new DownLoadImageTask(iv).execute(profile_image);
        Picasso.get()
                .load(profile_image)
                .into(iv);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(ranking);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                Log.d("onRatingChanged", "");
                UpdateTask ut = new UpdateTask(getApplicationContext(), null, user_id, (int) ratingBar.getRating());
                ut.execute();
            }
        });

    }

}