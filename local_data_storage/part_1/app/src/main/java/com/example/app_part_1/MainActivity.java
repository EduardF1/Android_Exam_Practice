package com.example.app_part_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String retrievedInput;
    private TextView textView;
    private EditText editText;
    private Button button;
    SharedPreferences preferences;  //  declare SharedPreferences object globally

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  initialize views
        textView = findViewById(R.id.textViewDisplay);
        editText = findViewById(R.id.editTextInput);
        button = findViewById(R.id.buttonDisplayInput);

        //  set button listener
        button.setOnClickListener(this);

        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE); // initialize the shared preferences object, give it a name and a file read mode (MODE_PRIVATE not accessible to other apps)
    }

    //  store the data (stored in a XML file) while for example a configuration change might occur or the activity would be interrupted
    @Override
    protected void onPause() {
        super.onPause();
        textView.setText(preferences.getString("input", "DEFAULT_NAME")); // get the preferences by key and if the preference does not exist, display "DEFAULT_NAME"
        editText.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText(preferences.getString("input", "DEFAULT_NAME")); // get the preferences by key and if the preference does not exist, display "DEFAULT_NAME"
    }

    @Override
    public void onClick(View v) {
        String editTextValue = editText.getText().toString(); // retrieve the input
        String textViewValue = textView.getText().toString(); // text view current value
        if(!editTextValue.equals(textViewValue))
        {
            textView.setText(editTextValue); // display in text view
            SharedPreferences.Editor editor = preferences.edit();   //  create a new Editor through which to add data (primitive types)
            editor.putString("input", editTextValue); // use the inner editor interface of the SharedPreferences to store the data (key, value)
            editor.apply(); // async update call (.commit() is sync)
        }
    }
}