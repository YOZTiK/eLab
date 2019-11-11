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

        final String id = ""+getIntent().getExtras().getString("index");
        String coname = getIntent().getExtras().getString("user_tag");
        String codesc = getIntent().getExtras().getString("desc");
        String imageUrl = getIntent().getExtras().getString("profile_image");
        final int ranking = Integer.parseInt(getIntent().getExtras().getString("rating"));

        Log.d("RATING", ranking+"");

        coName.setText(coname+"");
        coDescription.setText(codesc+"");

        new DownLoadImageTask(iv).execute(imageUrl);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(ranking);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                Log.d("onRatingChanged", "");
                UpdateTask ut = new UpdateTask(getApplicationContext(), null, id, (int) ratingBar.getRating());
                ut.execute();
            }
        });

    }

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}