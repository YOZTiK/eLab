package com.example.elab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.elab.ui.login.LoginActivity;

public class StartActivity extends AppCompatActivity {

    ImageView ivLetterE, ivIcon, ivLettersAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Handler handler = new Handler();

        ivLetterE = findViewById(R.id.ivLetterE);
        ivIcon = findViewById(R.id.ivIcon);
        ivLettersAB = findViewById(R.id.ivLettersAB);

        ivIcon.animate().alpha(1).setDuration(800).setStartDelay(50).start();

        ivLetterE.setTranslationX(400);
        ivLettersAB.setTranslationX(-430);
        ivLetterE.setTranslationY(-70);
        ivLettersAB.setTranslationY(-70);

        ivLetterE.animate().translationX(-10).alpha(1).setDuration(800).setStartDelay(400).start();
        ivLettersAB.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToNextActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(goToNextActivity);
            }
        }, 3000);

    }
}
