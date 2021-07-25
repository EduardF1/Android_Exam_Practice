package com.example.app_part_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button changeTextButton;
    private Switch aSwitch;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        changeTextButton = findViewById(R.id.button);
        aSwitch = findViewById(R.id.switch1);
        imageView = findViewById(R.id.imageView);

        changeTextButton.setText(getResources().getString(R.string.button_name));
        textView.setText(getResources().getString(R.string.original_text));
        imageView.setImageDrawable(getDrawable(R.drawable.ic_adb));
        changeTextButton.setOnClickListener(this);
        aSwitch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case (R.id.button):
            {
                textView.setText(getResources().getString(R.string.changed_text));
                break;
            }
            case (R.id.switch1):
            {
                if(aSwitch.isChecked())
                {
                    imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_ac));
                    break;
                }
                else {
                    imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_adb));
                    break;
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                textView.setText(getResources().getString(R.string.original_text));
            }
        };
        timer.schedule(timerTask, 0, 40000L);
    }

}

/*
    Q.10
    Create an Activity that changes the text of a TextView when a Button is pressed.
    All strings should be retrieved from the values folder.

    Consider:

    How do you access resources inside Activities?
    Is R.string.resourceName a String or an int?
    -   It is an int (unique identifier).

    When is getResources().getString() useful?
    -   It is useful as the OS determines at runtime which resource to use and in the case
    of say strings, it would determine the appropriate string value depending on the language
    (region) through localization.

    What advantages do you gain by not hardcoding your text in the Java code?
    -   Reusability, maintainability, separation of concerns and adaptivity of the resources to
    localization changes (assuming that there are alternative resources for the specified/used resource
    values).
    https://stackoverflow.com/questions/18641994/how-getresources-getstring-works-android
    https://stackoverflow.com/questions/15950291/hide-password-with-dots-for-an-edittext-in-android

    Q.11
    Make an ImageView change between two images using a Switch.

    Where do we store images in Android Studio?
    -   In the res/drawables package of the res directory

    Are you using isChecked()?
    -   Yes, the switch functions using one main concept, the slider that is set by default to off
    (left side), when changed to on (right side), isChecked() is set to true.

    What would happen if your image-names started with a capitalized letter or a number?
    -   we would either not be able to add it or we would get a compile error (as it is an
    unrecognized symbol)

    What kind of resources can be utilized in Android?
    -   Strings, Drawables (Images), mipmap (for the launcher), colors, dimens, styles, the app theme,
    layouts, menus etc.
 */