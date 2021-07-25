package com.example.app_part_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView tv1, tv2, tv3, tv4, tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.button):
                Intent intent = new Intent(this, MainActivity2.class);
                startActivity(intent);
                break;
            case (R.id.textView):
                if(tv1.getText().toString().equals("ALOHA"))
                    tv1.setText("HELLO WORLD!");
                else
                    tv1.setText("ALOHA");
                break;
            case (R.id.textView2):

                if(tv2.getText().toString().equals("PLLA"))
                    tv2.setText("HELLO WORLD!");
                else
                    tv2.setText("PLLA");
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv5 = findViewById(R.id.textView5);



        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);



    }

}
/*
    Q.14

    Create a new launcher icon for an application using Android Studio's build-in Asset Studio.

    Consider:
    What is the mipmap directory used for? Why does it contain multiple files with the same name?
    -   The mipmap directory is used for the application icons (launchers), these are the images
    that appear in the home screen on a phone for example. It also appears at the top of the app
    while it is in the background and any UI components that that do not have an image associated with
    them will use the mipmap resource image.
    - There are multiple files with the same content to adjust the size (by configuration qualifiers)
    according to the configuration of the screen (size), large screens, small screens etc...

    Q.15
    Create a new style for an app which contains TextViews. The style should define the size, color,
    font and style of the text, as well as making it all CAPS.

    Consider:
    How does layout/styles in Android compare to html/css in web design?
    -   As with web design, in Android, the layout is the UI skeleton (structure) of which's
    elements can be styled using styles (quite similar to web design's approach with html/css).

    Q.16
    Create a theme with new colors for the app- and status bar. Apply it to the main Activity.

    Consider:
    What is the difference between a style and a theme?
    A style should be applicable and used only for elements with similar semantics (specifications/
    attributes), ex.: multiple TextViews requiring the same attributes. We should never consider
    styles for only 1 element.

    Why might the AppCompat themes be useful?
    AppCompat themes are useful as well as AppCompatActivity (extension for java modules) for
    supporting older OS/API versions.

    Have you thought about following the material design color palette?
    Yes, as it is the official UI element styling guide.

    What is the difference between setting the theme on the Application and the Activity element
    in the Manifest?
    If set on the <activity>, only that specific activity will be using the specified theme whilst
    if set on the <application>, all activities will be using the specified theme.
 */
