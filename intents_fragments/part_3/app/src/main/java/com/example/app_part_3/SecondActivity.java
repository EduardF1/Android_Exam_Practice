package com.example.app_part_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        if(getIntent() != null){
            textView.setText("Message received");


            Bundle bundle = getIntent().getExtras();
            String receivedData = bundle.getString("message");
            textView2.setText(receivedData);
        }
    }
}