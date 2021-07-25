package com.example.app_part_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private int progress = 0;
    private Button button, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
       // button.setOnClickListener(this);
        //button2.setOnClickListener(this);
    }

    /*
    public void decrement(View view) {
        --progress;
        progressBar.setProgress(progress);
    }

    public void increment(View view) {
        ++progress;
        progressBar.setProgress(progress);
    }
*/
    @Override
    public void onClick(View v) {

    }

    public void makeSomething(View view) {
        switch (view.getId()) {
            case (R.id.button2): {
                ++progress;
                progressBar.setProgress(progress);
                break;
            }

            case (R.id.button):
            {
                --progress;
                progressBar.setProgress(progress);
                break;
            }
        }
    }
}

/*
Q.7

Create an app with two buttons and a ProgressBar. One button should increment the ProgressBar
 and one should decrement it. HINT: Use setProgress().

 Consider:
 Could the two buttons share the same OnClickListener or onClick attribute name?
 -  Yes, the 2 buttons could use the same OnClickListener, the XML button components are identified
 by their unique ids and the logic (code-wise) is handled in the same onClick method with
 differentiation made by view element id.
 */