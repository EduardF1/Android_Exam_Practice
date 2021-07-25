package com.example.app_part_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private final String TAG = "Main activity";
    private Toast toast;
    private int duration = toast.LENGTH_SHORT;
    private String text = "A toast...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast.makeText(context, text, duration).show();
            }
        });

        Log.i(TAG, "onCreate was called !");
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart was called!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume was called!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause was called!");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop was called!");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart was called!");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy was called!");

    }

    public void displayToast(View view) {
        Toast.makeText(MainActivity.this, text + "another toast..", duration).show();
    }
}



/*
    Q.2 Logging
    Create an info-log message in onCreate of your activity. Run the application and find the log.
    Consider:
    What methods does the Log-class have, and what arguments do they take ?
    - The Log class has the following methods (order by priority - highest to lowest):
    Log.e
    Log.w
    Log.i
    Log.d
    Log.v

    Consider:
    What methods does the Log-class have, and what arguments do they take?
    -   All of them take as parameters 2 Strings, the TAG (by convention a constant, it is an unique
    identifier) and the log message (Logcat runtime displayed message).
    How do you monitor the logs and how do you filter to only show specific log messages?
    -   The logcat runtime messages/logs are monitored by accessing the Logcat pane in the bottom
    menu pane of the IDE, for visualizing specific messages we select the logcat message type (verbose,
    info etc.. located next to the regex search tab). We can also search for keywords using the Regex
    search tab and configure different settings in the right drop-down menu of the Logcat menu pane.
    Furthermore, we can configure customized filters.

    Q.3 The Activity Lifecycle
    When is each method in the activity lifecycle called?
    Utilize the Log class to find out what happens when:

    a) The application is started
    As expected, onCreate - onStart - onResume (Running app. state) are called sequentially

    b) The home button is pressed
    As expected, onPause is called initially and within ca. 1 ms, onStop is called (The activity is
    placed in the background). Upon selecting the activity from the background, onRestart - onStart -
    onResume are called sequentially.

    c)  Receiving a SMS
    The activity maintains its Running state without changes. The SMS message appeared for a short
    time at the top of the screen.

    d)  Receiving a phone call
    When receiving the phone call, no changes happened, however, when answering the call, the
    activity entered a paused state, onPause was called, followed by onStop ca. 1 ms later.

    e)  Pressing the back button
    The activity was destroyed (however, still accessible in the background). Upon selecting it from
    the background, onCreate - onStart - onResume were called sequentially.

    f) Closing the application with the task manager
    onDestroy is called

    g) Rotating the device
    The activity lifecycle callbacks : onPause - onStop - onDestroy - onCreate - onStart - onResume

    Consider:
    Can you explain the results shown for e.i. rotating the device?
    Yes, upon device rotation, the configuration is changed, hence, the activity is initially
    paused-stopped-destroyed and then created-started-resumed.

    Do you know the purpose of each callback method?
    onCreate() - called when the activity is launched
    onStart() - follows the onCreate() callback almost instantly
    onResume() - follows the onStart() callback (the activity is now in running state)
    onPause() - the activity is paused by some user action, it still exists in the background
    (invisible, it will become stopped quite fast)
    onStop() - the activity is stopped, still exists but it enters this an inactive state
    (initial state of removal)
    onDestroy() - the activity is destroyed either because it is finished or the system removes it

    What is the shortcut to override the callback methods of the activity lifecycle?
    Ctrl + O

    Q.4
    Utilize an OnClickListener to make a Button display a Toast when it is clicked.

    Consider:
    What options do you have for setting the Context as the first argument in makeText()?
    Would this work in an inner class? Why/why not?
    - We can set up the Context either directly by calling getApplicationContext() or through a
    member component - variable. It will work but we need to use the className.this

    https://stackoverflow.com/questions/29811106/android-make-toast-or-dialog-inside-static-inner-class-of-activity

    Can the duration of a Toast be anything else than LENGTH_SHORT and LENGTH_LONG?
    -   It is possible to have a different length for a Toast by using a helper class (it will make
    use of a timer).
    https://stackoverflow.com/questions/2220560/can-an-android-toast-be-longer-than-toast-length-long

    Q.5

    Consider:
    What is the difference between using OnClickListeners in Java and the onClick attribute in XML?
    Do you have a preference?
   https://stackoverflow.com/questions/21319996/android-onclick-in-xml-vs-onclicklistener/33972753#:~:text=Difference%20Between%20OnClickListener%20vs%20OnClick,what%20happens%20when%20someone%20clicks.

    Can the onClick attribute be used on e.i. a TextView or ImageView as well?
    Yes, as all the UI elements are Views.
 */