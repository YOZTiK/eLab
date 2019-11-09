package com.example.elab;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeAct extends AppCompatActivity {

    TextView textView2, ivItemOneTitle, ivItemOneSubTitle;
    ImageView ivIlls;
    Animation smalltobig, smalltobig2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        smalltobig2 = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface mlight = Typeface.createFromAsset(getAssets(),"fonts/MontserratLight.ttf");
        Typeface mmedium = Typeface.createFromAsset(getAssets(),"fonts/MontserratMedium.ttf");
        Typeface mregular = Typeface.createFromAsset(getAssets(),"fonts/MontserratRegular.ttf");

        ivIlls = findViewById(R.id.ivIlls);

        textView2 = findViewById(R.id.textView2);
        ivItemOneTitle = findViewById(R.id.ivItemOneTitle);
        ivItemOneSubTitle = findViewById(R.id.ivItemOneSubTitle);

        textView2.setTypeface(mlight);
        ivItemOneTitle.setTypeface(mregular);
        ivItemOneSubTitle.setTypeface(mlight);

        textView2.setTranslationX(400);
        ivItemOneTitle.setTranslationX(400);
        ivItemOneSubTitle.setTranslationX(400);

        textView2.setAlpha(0);
        ivItemOneTitle.setAlpha(0);
        ivItemOneSubTitle.setAlpha(0);

        textView2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        ivItemOneTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ivItemOneSubTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        ivIlls.startAnimation(smalltobig2);

    }
}
