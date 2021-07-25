package com.example.app_part_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView,textView2;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.text_view_second_activity_status);
        textView2 = findViewById(R.id.text_view_second_activity_message);
        editText = findViewById(R.id.editText_secondActivity);
        button = findViewById(R.id.buttonSecondActivity);
        button.setOnClickListener(this);

        Log.i("Second Activity", "onCreate() called");

        if(getIntent() != null)
        {
            textView.setText("Message received");
            Bundle bundle = getIntent().getExtras();
            textView2.setText(bundle.getString("messageFromMainActivity"));
        }

    }

    @Override
    public void onClick(View v) {
        if(!editText.getText().toString().equals(""))
        {
            Intent intent = new Intent(); // create new Intent object
            intent.putExtra("replyFromSecondActivity", editText.getText().toString()); // put input in the intent's bundle

            /*
             * @param resultCode The result code to propagate back to the originating activity,
               often RESULT_CANCELED or RESULT_OK

               @param data The data to propagate back to the originating activity.
             */
            setResult(RESULT_OK, intent);
            finish();   //  activity done (as we are in the Running state) the lifecycle calls are: onPause() - onStop() - onDestroy()
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Second Activity", "onStart() called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Second Activity", "onResume() called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Second Activity", "onPause() called");

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Second Activity", "onStop() called");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Second Activity", "onDestroy() called");

    }

}