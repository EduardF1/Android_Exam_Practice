package com.example.app_part_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextMessage);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(!editText.getText().toString().equals("")){
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("message", editText.getText().toString());
            startActivity(intent);
        }
    }
}

/*
    Q.4
    Extend your app from the previous exercise to also send data along with the Intent, gathered
    from an EditText field. Have the second Activity retrieve this data and display it on the screen.

    Consider:
    What method is used to add extra data to an Intent?
    -   The method being used is putExtra(), it takes 2 arguments, key and value, the key is always
    a String, however the value can be of multiple types (primitive - String, int,float etc..).

    What method is used to retrieve this same data in the receiving Activity?
    -   The method used to retrieve the same data is getExtras(), it returns a data bundle with
    the previously send data. From there on, the data can be specifically retrieved.

    Why do we need to set a key along with the data to be sent?
    -   So that the data sent is uniquely identified in the mapping (within the bundle).
    We could send multiple Strings, ints etc.. in order to distinguish between the sent items we
    need a key for each.
 */