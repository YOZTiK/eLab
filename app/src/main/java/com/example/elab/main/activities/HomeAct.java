package com.example.elab.main.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elab.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeAct extends AppCompatActivity {

    TextView textView2, ivItemOneTitle, ivItemOneSubTitle, ivItemTwoTitle, ivItemTwoSubTitle, ivItemThreeTitle, ivItemThreeSubTitle, ivItemFourTitle, ivItemFourSubTitle;
    ImageView ivIlls;
    Animation smalltobig, smalltobig2;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        smalltobig2 = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface mlight = Typeface.createFromAsset(getAssets(),"fonts/MontserratLight.ttf");
        Typeface mmedium = Typeface.createFromAsset(getAssets(),"fonts/MontserratMedium.ttf");
        Typeface mregular = Typeface.createFromAsset(getAssets(),"fonts/MontserratRegular.ttf");

        ivIlls = findViewById(R.id.ivIlls);

        textView2 = findViewById(R.id.textView2);
        ivItemOneTitle = findViewById(R.id.ivItemOneTitle);
        ivItemOneSubTitle = findViewById(R.id.ivItemOneSubTitle);
        ivItemTwoTitle = findViewById(R.id.ivItemTwoTitle);
        ivItemTwoSubTitle = findViewById(R.id.ivItemTwoSubTitle);
        ivItemThreeTitle = findViewById(R.id.ivItemThreeTitle);
        ivItemThreeSubTitle = findViewById(R.id.ivItemThreeSubTitle);
        ivItemFourTitle = findViewById(R.id.ivItemFourTitle);
        ivItemFourSubTitle = findViewById(R.id.ivItemFourSubTitle);

        textView2.setTypeface(mlight);
        ivItemOneTitle.setTypeface(mregular);
        ivItemOneSubTitle.setTypeface(mlight);
        ivItemTwoTitle.setTypeface(mregular);
        ivItemTwoSubTitle.setTypeface(mlight);
        ivItemThreeTitle.setTypeface(mregular);
        ivItemThreeSubTitle.setTypeface(mlight);
        ivItemFourTitle.setTypeface(mregular);
        ivItemFourSubTitle.setTypeface(mlight);

        textView2.setTranslationX(400);
        ivItemOneTitle.setTranslationX(400);
        ivItemOneSubTitle.setTranslationX(400);
        ivItemTwoTitle.setTranslationX(400);
        ivItemTwoSubTitle.setTranslationX(400);
        ivItemThreeTitle.setTranslationX(400);
        ivItemThreeSubTitle.setTranslationX(400);
        ivItemFourTitle.setTranslationX(400);
        ivItemFourSubTitle.setTranslationX(400);

        textView2.setAlpha(0);
        ivItemOneTitle.setAlpha(0);
        ivItemOneSubTitle.setAlpha(0);
        ivItemTwoTitle.setAlpha(0);
        ivItemTwoSubTitle.setAlpha(0);
        ivItemThreeTitle.setAlpha(0);
        ivItemThreeSubTitle.setAlpha(0);
        ivItemFourTitle.setAlpha(0);
        ivItemFourSubTitle.setAlpha(0);

        textView2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        ivItemOneTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ivItemOneSubTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        ivItemTwoTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ivItemTwoSubTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        ivItemThreeTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ivItemThreeSubTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        ivItemFourTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ivItemFourSubTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        ivIlls.startAnimation(smalltobig2);
    }

    public void goToSearch(View view) {

        Intent goToNextActivity = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(goToNextActivity);

    }

    public void goToRegistry(View view) {

        Intent goToNextActivity = new Intent(getApplicationContext(), RegistryActivity.class);
        startActivity(goToNextActivity);

    }

    public void goToUsersControl(View view) {

        Intent goToNextActivity = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(goToNextActivity);
        //Instead of users control...

    }

    public void goToInfo(View view) {

        Intent goToNextActivity = new Intent(getApplicationContext(), WebViewActivity.class);
        startActivity(goToNextActivity);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
