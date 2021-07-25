package com.example.app_part_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView, textView2, textView3;
    private ImageView imageView, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeResources();
    }

    private void initializeResources(){
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView1);
        textView3 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        setValues();
    }

    private void setValues(){
        textView.setText(getResources().getString(R.string.string_0));
        textView2.setText(getResources().getString(R.string.string_1));
        textView3.setText(getResources().getString(R.string.string_2));
        imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_anchor));
        imageView2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_cart));
    }
}

/*
    Q.13
    Create an Activity that uses two different layouts depending on whether it is in landscape or
    portrait mode.

    Consider:
    What configuration qualifiers can you use to support different resources for multiple devices?
    - For smallest width we use sw<N>dp where N can be for example 320, 600, 720.
    - For language and region we use en,da,de and da-rDA, en-rUK, de-rDE etc.
    - For screen orientation, we use land (landscape) or port (portrait)
    - For screen pixel density (dpi), we use ldpi,mdpi,hdpi,xhdpi,xxhdpi,xxxhdpi,nodpi,tvdpi,anydpi
 */