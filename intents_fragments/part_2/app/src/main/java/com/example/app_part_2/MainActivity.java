package com.example.app_part_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private String messageToSend = "Message received";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        /*  Intent constructor parameters (source, target)
            As this is an explicit intent, we know clearly/specifically to which app
            component to navigate.

             @param packageContext A Context of the application package implementing this class.
             @param cls The component class that is to be used for the intent.

         */
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("message", messageToSend);
        startActivity(intent);
    }
}

/*
    Q3. Create an app which displays a Button. Clicking the Button should send an explicit Intent
     to start a new Activity within the app.

     Consider:
     What two inputs are required when creating an explicit Intent?
     - The two required inputs (Context, class) are the source (activity context that sends the intent) and target (activity
     that receives the intent). As with explicit intents, no intent-filters are required as we
     explicitly know were to navigate. In order to navigate back to the source activity, the
     back button, navigation component, manifest specification - <activity android:name=".SecondActivity" android:parentActivityName=".MainActivity">
     or finish() can be used.
     - Furthermore, data is sent (in this case a simple String) through adding to the intent
     Extras by calling putExtra() - it takes a key, value pair and functions for simple data types
     (not compatible with serializable data). Then, to receive the data, the activity that handles
     the intent needs to retrieve the intent's Extras and retrieve the specified/sent data through
     a type-specific getter and the key of the sent Extra.
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString("message"));

     What does this refer to in the constructor of an Intent?
     - This refers to the the first parameter as being the application package context of the app
     and the second being the application component to which the intent is sent (which handles the
     intent).
 */