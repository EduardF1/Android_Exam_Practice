package com.example.app_part_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.loginButton);
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);

        button.setText(R.string.string_p);
        button.setOnClickListener(this);
        usernameEditText.setOnClickListener(this);
        passwordEditText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.loginButton): {
                if (usernameEditText.getText().toString().equals("user@email.com") &&
                        passwordEditText.getText().toString().equals("ILOVEAND")) {
                    Toast.makeText(this.getApplicationContext(), "Welcome to the app !", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

/*

    Q.8
    Create a login screen with an email and password field, as well as a login button.
    The Button should create a welcoming Toast if the login information was:
    Email: user@email.com
    Password: ILOVEAND

    Consider:
    How do you retrieve information from a password field?
    -   As a password needs to be hidden (the characters), we use the android:inputType="textPassword"
    attribute.
    Using getText() returns a CharSequence - how would you convert this to a String for easy comparison?
    -   One way to approach this is to call .toString() on the CharSequence.

    Q.9
    Create a new String resource in the values folder with the value "AND IS AWESOME".
    Create two TextViews in your layout and use this String resource in both of them.
    Then give the TextViews a background color, that you define in colors.xml.

    Consider:
    What is the purpose of externalizing resources?
    Enhanced maintainability, separation of concerns, application robustness and reusability among
    the resources.

    Is it possible to store Strings in e.i. colors.xml? Why/why not?
    - It is possible, as within the <resources> attribute, we can store multiple types, however,
    this is not recommended.
 */