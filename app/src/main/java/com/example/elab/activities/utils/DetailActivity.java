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

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {
    UserDatabase db;
    UserViewModel users;

    String user_id;
    String profile_image;
    String user_tag;
    String name;
    String last_name;
    String status;
    String user_type;
    String bachelor_degree;
    int ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        TextView coName = findViewById(R.id.companyName);
        TextView coDescription = findViewById(R.id.companyDescription);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        users = ViewModelProviders.of(this).get(UserViewModel.class);

        user_id = ""+getIntent().getExtras().getString("user_id");
        profile_image = getIntent().getExtras().getString("profile_image");
        user_tag = getIntent().getExtras().getString("user_tag");
        name = getIntent().getExtras().getString("name");
        last_name = getIntent().getExtras().getString("last_name");
        status = getIntent().getExtras().getString("status");
        user_type = getIntent().getExtras().getString("user_type");
        bachelor_degree = getIntent().getExtras().getString("bachelor_degree");
        ranking = Integer.parseInt(getIntent().getExtras().getString("ranking"));

        System.out.println("Received in detail activity: "+user_id+" "+profile_image+" "+user_tag+" "+name+" "+last_name+" "+status+" "+user_type+" "+bachelor_degree+" "+ranking);

        coName.setText(name+"");
        coDescription.setText(user_tag+"");

        new DownLoadImageTask(iv).execute(profile_image);

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

    public void modifyUser(View view) {
        /*Send the info to another screen to modify...*/
        System.out.println("Received in detail activity: "+user_id+" "+profile_image+" "+user_tag+" "+name+" "+last_name+" "+status+" "+user_type+" "+bachelor_degree+" "+ranking);

        Intent i = new Intent(this, ModifyUserActivity.class);
        //Log.d("RESULT PRODUCTS!!!", "RESULT OF SEARCH: " + resultProducts);
        //Log.d("NAME PRODUCT", "RESULT OF SELECT: " + resultProducts.get(adapterPosition).user_tag);
        //Log.d("DESCRIPTION PRODUCT", "RESULT OF SELECT: " + resultProducts.get(adapterPosition).user_name);

        i.putExtra("user_id", user_id);
        i.putExtra("profile_image", profile_image);
        i.putExtra("user_tag", user_tag);
        i.putExtra("name", name);
        i.putExtra("last_name", last_name);
        i.putExtra("status", status);
        i.putExtra("user_type", user_type);
        i.putExtra("bachelor_degree", bachelor_degree);
        i.putExtra("ranking", ranking);

        startActivity(i);
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