package com.example.elab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);


    }

    @Override
    public void handleResult(Result rawResult) {
        RegistryActivity.tvResult.setText(rawResult.getText());
        onBackPressed();
    }

    @Override
    public  void onResume(){
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    protected void onPause(){
        super.onPause();
        scannerView.stopCamera();
    }
}
