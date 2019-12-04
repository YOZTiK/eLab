package com.example.elab.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elab.R;
import com.example.elab.database.Gacmer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistryActivity extends AppCompatActivity {

    public static TextView tvResult;
    public static Gacmer gacmer;

    private DatabaseReference mDatabase;

    Button btnScanner;
    public static Button btnSaveUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        tvResult = findViewById(R.id.tvResult);
        btnScanner = findViewById(R.id.btnScanner);
        btnSaveUser = findViewById(R.id.btnSave);

        btnSaveUser.setEnabled(false);

    }

    public void saveInFirebase(View view){
        //SAVE THE USER IN FIREBASE!
        mDatabase.child("gacmers").child(gacmer.user_id).setValue(gacmer);
        Toast.makeText(RegistryActivity.this, "User saved successfully!", Toast.LENGTH_LONG).show();
    }

    public void goToReadQR(View view) {
        int requestCode = 0;
        ActivityCompat.requestPermissions(RegistryActivity.this, new String[] {Manifest.permission.CAMERA}, requestCode);

        startActivity(new Intent(RegistryActivity.this, ScannerActivity.class));
    }
}
