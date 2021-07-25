package com.example.app_part_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView, textView2, textView3, textView4, textView5, textView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);

        textView.setText(getResources().getString(R.string.string_1));
        textView2.setText(getResources().getString(R.string.string_2));
        textView3.setText(getResources().getString(R.string.string_3));
        textView4.setText(getResources().getString(R.string.string_3));
        textView5.setText(getResources().getString(R.string.string_4));
        textView6.setText(getResources().getString(R.string.string_5));
    }
}

/*
    Q.12
    Create an app with a few TextViews that supports two languages.

    Consider:
    How do you access the translations editor?
    -   By accessing the res/strings/strings.xml file and selecting from the top menu pane the
    "open editor" option. From there on, we can select any language - region or language for the
    strings we are using.

    How do you test that both languages work as intended?
    -   By firstly, providing alternative resources for the strings and then changing the language
    of the emulator/device.
 */