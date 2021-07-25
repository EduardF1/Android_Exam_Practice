package com.example.app_part_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND); // implicit intent, parameters (action type)
        intent.setType("text/plain"); // set MIME data type

        //  constants added to the intent data bundle
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"fischerszavaeduard@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "A mail from the app");
        intent.putExtra(Intent.EXTRA_TEXT,"I am who I am");

        Intent chooser = Intent.createChooser(intent, null); // specify the use of a chooser

        if(intent.resolveActivity(getPackageManager()) != null) // verify that at least 1
        startActivity(chooser);
    }
}

/*
    Q.6
    Create an app which uses an implicit Intent to send an email. Have the Intent contain
    information for receiver email, subject and a text body.

    Consider:
    What is the difference between an implicit and explicit Intent?
    - The difference is that while explicit intents do not require intent filters and have a specific
    app component target to perform the task needed (do the job), implicit intents are more loose
    meaning that they specify the type of job to be done ex.: ACTION_SEND through using a chooser
    intent, the user will be able to select the system app. to handle the sending of the email.

    What type of functionality is typically available with implicit Intents?
    -   Several actions can be performed (ACTION_SEND, ACTION_VIEW etc.), choosing the app. to
    handle the intent as the OS determines which apps are candidates for handling the intent based
    on their intent filter criteria.

 */