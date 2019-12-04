package com.example.elab.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.elab.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView feedbackformlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        setTitle("Peticiones y reportes");

        feedbackformlink = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = feedbackformlink.getSettings();

        feedbackformlink.setInitialScale(200);
        feedbackformlink.getSettings().setSupportZoom(true);
        feedbackformlink.getSettings().setLoadWithOverviewMode(true);
        feedbackformlink.getSettings().setBuiltInZoomControls(true);

        webSettings.setJavaScriptEnabled(true);
        feedbackformlink.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSeBnacHLkzD5MoNfOJXA3x8oh8Yfs2-ZZ9StLEK7GZqNL37mA/viewform");
        feedbackformlink.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (feedbackformlink.canGoBack()){
            feedbackformlink.goBack();
        } else
            super.onBackPressed();
    }

}
