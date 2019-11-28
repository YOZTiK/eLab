package com.example.elab.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elab.R;

public class users_contol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_users_contol);
    }

    public void goToUserRegistry(View view) {
        Intent goToNextActivity = new Intent(getApplicationContext(), UserRegistryActivity.class);
        startActivity(goToNextActivity);
    }

    public void goToSearchModify(View view) {
        Intent goToNextActivity = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(goToNextActivity);
    }
}
