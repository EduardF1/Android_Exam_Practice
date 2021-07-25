package com.example.app_part_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView, textView1;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view_receive_status_main_activity);
        textView1 = findViewById(R.id.text_view_receive_main_activity);
        button = findViewById(R.id.buttonMainActivity);
        editText = findViewById(R.id.editText_mainActivity);

        button.setOnClickListener(this);

        Log.i("Main Activity", "on Create() called");
    }


    @Override
    public void onClick(View v) {
        //  explicit intent
        Intent intent = new Intent(this, SecondActivity.class);
       // String key = "messageFromMainActivity";
        String value = editText.getText().toString();
        intent.putExtra("messageFromMainActivity", value);
        startActivityForResult(intent,1); // start the activity and expect a result
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Main Activity", "onStart() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Main Activity", "onReStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main Activity", "onResume() called");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Main Activity", "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Main Activity", "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main Activity", "onDestroy() called");
    }

    /*
                    Request code must match that of the sent intent, the resultCode is set (with setResult())
                    in the target activity of the request.
                 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                textView.setText("Reply received");
                textView1.setText(data.getExtras().getString("replyFromSecondActivity"));
            }
        }
    }
}

/*
    Q.5
    Extend your app from previous exercise to have the second Activity send a reply back to the
    first Activity. Display the reply in the first Activity.

    Consider:
    What is the difference between using finish()and clicking the back button, if any?
    -   Several differences exist, one is that finish() is called through the code, secondly,
    although it can be overwritten through code (onBackPressed()), the back button is visual to the
    user whereas finish() is not. Moreover, when finish() is called, onDestroy() follows.

    Calling finish() in onCreate(): onCreate() -> onDestroy()
    Calling finish() in onStart() : onCreate() -> onStart() -> onStop() -> onDestroy()
    Calling finish() in onResume(): onCreate() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroy()

    Also, after the Second Activity is finished, we cannot go back through the back button.

    What does the back stack look like?
    Main Activity -> Second Activity, Main Activity -> MainActivity
    The transition process occurs like this:
    The Main Activity is launched (onCreate, onStart, onResume) and as soon as the Intent targeting
    the Second Activity is sent it is sent to below the Second Activity (now on top of the back stack - onCreate(), onStart(), onResume()),
    the Main Activity enters a stopped state (onPause() - onStop()). As soon as the intent is sent from the
    Second Activity, the Second Activity is destroyed (onPause(), onStop(), onDestroy() being called
    sequentially, this is because though we set our listener in the onCreate(), it triggers while
    the Second Activity is Running). Finally, the Second Activity is removed (onDestroy()) from
    the top of the Back Stack and the Main Activity is pushed to the top entering a Running state
    (from onPause() to onRestart() - onStart() - onResume()).

    Which life cycle methods are invoked and when?
 */