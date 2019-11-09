package com.example.elab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashAct extends AppCompatActivity {

    TextView ivLogo, ivSubtitle, ivBtn;
    ImageView ivSplash;
    Animation smalltobig, fleft, fhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        fleft = AnimationUtils.loadAnimation(this, R.anim.fleft);
        fhelper = AnimationUtils.loadAnimation(this, R.anim.fhelper);
//        fromleftoright = AnimationUtils.loadAnimation(this, R.anim.fromlefttoright);

        Typeface logox = Typeface.createFromAsset(getAssets(),"fonts/Fredoka.ttf");
        Typeface mlight = Typeface.createFromAsset(getAssets(),"fonts/MontserratLight.ttf");
        Typeface mmedium = Typeface.createFromAsset(getAssets(),"fonts/MontserratMedium.ttf");
        Typeface mregular = Typeface.createFromAsset(getAssets(),"fonts/MontserratRegular.ttf");

        ivLogo = findViewById(R.id.ivLogo);
        ivSubtitle = findViewById(R.id.ivSubtitle);
        ivBtn = findViewById(R.id.ivBtn);

        ivSplash = findViewById(R.id.ivSplash);

        ivLogo.setTypeface(logox);
        ivSubtitle.setTypeface(mlight);
        ivBtn.setTypeface(mmedium);

//        ivSplash.setScaleX(0);
//        ivSplash.setScaleY(0);

        ivSplash.startAnimation(smalltobig);

//        ivLogo.startAnimation(fromleftoright);
//        ivSubtitle.startAnimation(fromleftoright);
//        ivBtn.startAnimation(fromleftoright);

        ivLogo.setTranslationX(400);
        ivSubtitle.setTranslationX(400);
        ivBtn.setTranslationX(400);

        ivLogo.setAlpha(0);
        ivSubtitle.setAlpha(0);
        ivBtn.setAlpha(0);

        ivLogo.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        ivSubtitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ivBtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        ivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ax = new Intent(SplashAct.this,HomeAct.class);
                startActivity(ax);
                overridePendingTransition(R.anim.fleft, R.anim.fhelper);
            }
        });

    }
}
