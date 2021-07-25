package com.example.app_part_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);
    }

    public void changeText(View view) {
        textView.setText(editText.getText());
    }
}

/*

    Q.6
    Create an app as shown in the image below. When the Button is pressed the text from the
    EditText should be displayed in the TextView. Hint: Use setText() and getText().

    Consider:
    What is the difference between the attributes hint and text of EditText?

    The difference is that the hint attribute is a faded placeholder that as soon as input is given,
    is removed (hided) and the text attribute is a placeholder that will set a default value for the
    input.
    
 */