package com.example.elab.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;
import android.widget.Toast;

import com.example.elab.database.User;
import com.example.elab.main.activities.RegistryActivity;
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

        Boolean isCorrect = true;

        String[] arrOfStr = rawResult.getText().split(",");
        for (String a : arrOfStr){
            System.out.println(a);
            if(a == null){
                isCorrect = false;
            }
        }

        if(isCorrect){
            //Set the gacmer in the other activity.
            RegistryActivity.user = new User(
                    arrOfStr[0],
                    arrOfStr[1],
                    arrOfStr[2],
                    arrOfStr[3],
                    arrOfStr[4],
                    arrOfStr[5],
                    arrOfStr[6],
                    arrOfStr[7],
                    Integer.parseInt(arrOfStr[8])
            );

            //Set the text.
            RegistryActivity.tvResult.setText(
                            arrOfStr[0]+"\n"+
                            arrOfStr[2]+"\n"+
                            arrOfStr[3]+"\n"+
                            arrOfStr[4]+"\n"+
                            arrOfStr[5]+"\n"+
                            arrOfStr[6]+"\n"+
                            arrOfStr[7]+"\n"+
                            Integer.parseInt(arrOfStr[8]));

            //IMAGE : arrOfStr[1]

            //Enable the button.
            RegistryActivity.btnSaveUser.setEnabled(true);
        }else{
            Toast.makeText(ScannerActivity.this, "User info incorrect...", Toast.LENGTH_LONG).show();
        }


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
