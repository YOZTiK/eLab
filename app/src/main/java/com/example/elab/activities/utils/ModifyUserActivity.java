package com.example.elab.activities.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.elab.R;
import com.example.elab.database.UpdateTask;
import com.example.elab.database.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class ModifyUserActivity extends AppCompatActivity {

    String user_id;
    String profile_image;
    String user_tag;
    String user_name;
    String last_name;
    String status;
    String user_type;
    String bachelor_degree;
    int ranking;

    private DatabaseReference mDatabase;

    EditText nameET;
    EditText lastNameET;
    EditText statusET;
    EditText userTypeET;
    EditText bachelorDegreeET;
    RatingBar ratingBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        user_id = ""+getIntent().getExtras().getString("user_id");
        profile_image = getIntent().getExtras().getString("profile_image");
        user_tag = getIntent().getExtras().getString("user_tag");
        user_name = getIntent().getExtras().getString("user_name");
        last_name = getIntent().getExtras().getString("last_name");
        status = getIntent().getExtras().getString("status");
        user_type = getIntent().getExtras().getString("user_type");
        bachelor_degree = getIntent().getExtras().getString("bachelor_degree");
        ranking = getIntent().getExtras().getInt("ranking");

        nameET = findViewById(R.id.nameEditText);
        lastNameET = findViewById(R.id.lastNameEditText);
        statusET = findViewById(R.id.statusEditText);
        userTypeET = findViewById(R.id.userTypeEditText);
        bachelorDegreeET = findViewById(R.id.bachelorDegreeEditText);
        ratingBar = findViewById(R.id.ratingBar);
        imageView = findViewById(R.id.userImage);

        nameET.setText(user_name);
        lastNameET.setText(last_name);
        statusET.setText(status);
        userTypeET.setText(user_type);
        bachelorDegreeET.setText(bachelor_degree);
        Picasso.get()
                .load(profile_image)
                .into(imageView);
        ratingBar.setRating(ranking);
    }

    public void saveModifiedUser(View view) {

        if(nameET.getText().length() == 0 ||
                lastNameET.getText().length() == 0 ||
                statusET.getText().length() == 0 ||
                userTypeET.getText().length() == 0 ||
                bachelorDegreeET.getText().length() == 0
        ){
            Toast.makeText(ModifyUserActivity.this, "Hay un valor en blanco...", Toast.LENGTH_LONG).show();
            return;
        }

        User gacmer = new User(
                user_id,
                profile_image,
                user_tag,
                nameET.getText().toString(),
                lastNameET.getText().toString(),
                statusET.getText().toString(),
                userTypeET.getText().toString(),
                bachelorDegreeET.getText().toString(),
                (int)ratingBar.getRating()
        );
        mDatabase.child("gacmers").child(gacmer.user_id).setValue(gacmer);
        Toast.makeText(ModifyUserActivity.this, "Usuario actualizado!", Toast.LENGTH_LONG).show();
    }
}
