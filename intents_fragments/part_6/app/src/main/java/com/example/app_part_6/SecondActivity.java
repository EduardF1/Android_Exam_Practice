package com.example.app_part_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class SecondActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        webView = findViewById(R.id.webView);

        // receive the Implicit Intent
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri uri = intent.getData();
        webView.loadUrl(uri.toString());

        Log.i("Action", "action: " + action);
        Log.i("type", "data: " + uri);
    }
}