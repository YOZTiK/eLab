package com.example.elab.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elab.R;

public class RegistryActivity extends AppCompatActivity {

    public static TextView tvResult;
    Button btnScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        tvResult = findViewById(R.id.tvResult);
        btnScanner = findViewById(R.id.btnScanner);

    }

    public void goToReadQR(View view) {
        int requestCode = 0;
        ActivityCompat.requestPermissions(RegistryActivity.this, new String[] {Manifest.permission.CAMERA}, requestCode);

        startActivity(new Intent(RegistryActivity.this, ScannerActivity.class));
    }
}
