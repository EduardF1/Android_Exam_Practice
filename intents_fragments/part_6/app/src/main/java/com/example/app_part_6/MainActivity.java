package com.example.app_part_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextURL);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!editText.getText().toString().equals(""))
        {
            String action = Intent.ACTION_VIEW;
            Uri uri = Uri.parse("http://" + editText.getText().toString());
            Intent intent = new Intent(action, uri);
            startActivity(intent);
        }
    }
}

/*
    Q.7
    Implement an app that registers itself as a web browser so that it can display web pages for
    other apps. Have the app show a requested web page in a WebView through an implicit Intent.

    Consider:
    If you're stuck on creating the WebView, have a look through the WebView API.
    - A WebView widget simply displays web content.

    What is the role of the manifest in this context?
    -   The manifest through the <activity> (in this case, Second Activity), will be the
    web client (receiver/handler of the implicit intent). In order for the intent to pass through
    the filter, it needs to match the <action> (required attribute), <category> and <data> subelements.

     <activity android:name=".SecondActivity">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.BROWSABLE" />
                <category android:name="android.intent.category.DEFAULT" />
                <data android:scheme="http" />
            </intent-filter>
        </activity>

    Certain intents require permissions to be set in the AndroidManifest.xml file. Why is this?
    -   Mainly to protect the system's integrity and user privacy. Each app runs in a limited
    access sandbox. These permissions are in the form of:
        <uses-permission android:name="android.permission.INTERNET" />

 */