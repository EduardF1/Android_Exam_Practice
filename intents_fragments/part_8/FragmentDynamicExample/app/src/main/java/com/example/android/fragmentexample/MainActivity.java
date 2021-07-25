/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.open_button);

        button.setOnClickListener(this);

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                button.setText(R.string.close);
            }
        }
    }

    public void displayFragment(){

        //  retrieve fragment instance
        SimpleFragment simpleFragment = SimpleFragment.newInstance();

        //  get the FragmentManager and start a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Add the SimpleFragment to the previously started transaction
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).
                commit();

        //  Update the Button text
        button.setText(R.string.close);
        //  Set boolean flag to indicate that the fragment is open
        isFragmentDisplayed = true;
    }

    public void closeFragment(){
        //  Get the Fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if(simpleFragment != null){
            //  Create and commit the transaction to remove the fragment
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        //  Update the Button text
        button.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    @Override
    public void onClick(View view) {
        if(!isFragmentDisplayed){
            displayFragment();
        }
        else {
            closeFragment();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }
}

/*
    Q.8
    Get familiar with Fragments by following Google's official codelab on the topic.

    Consider:
    What is the difference between an Activity and a Fragment?
    -   A Fragment is a modular section of an Activity. They have an individual lifecycle and are of
    2 types, dynamic (replacable at runtime within a specified container) or
    static (cannot be replaced at runtime).

    What is the difference between a static and dynamic Fragment?
    -   At runtime, dynamic fragments can be replaced in their specified container whilst static
    fragments cannot.

    What is the concept behind a single Activity application?
    What are the advantages of using Fragments?
    -   Modularization of UI elements at a greater extent, reusability of UI elements (more dynamic
    and flexible). These are also interchangable elements. The backstack of the Activity manages the
    fragments used by the activity.

    What is the purpose of the FragmentManager?
    -   To handle fragment transactions, add, remove, delete, replace fragments
 */
