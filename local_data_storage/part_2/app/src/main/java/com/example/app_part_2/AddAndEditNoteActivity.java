package com.example.app_part_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddAndEditNoteActivity extends AppCompatActivity {

    //  Log cat message identifier
    private static final String TAG = "AddAndEditNoteActivity";

    // constants for retrieving the bundle data from the startActivityForResult()
    public static final String INTENT_EXTRA_TITLE_KEY = "KEY_EXPLICIT_INTENT_TITLE";
    public static final String INTENT_EXTRA_DESCRIPTION_KEY = "KEY_EXPLICIT_INTENT_DESCRIPTION";
    public static final String INTENT_EXTRA_PRIORITY_KEY = "KEY_EXPLICIT_INTENT_PRIORITY";
    public static final String INTENT_EXTRA_NOTE_ID_KEY = "KEY_EXPLICIT_INTENT_NOTE_ID"; // for accessing the Note in the Room database

    private EditText editTextNoteTitle;
    private EditText editTextNoteDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextNoteTitle = findViewById(R.id.edit_text_note_title);
        editTextNoteDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);


        // receive the edit note intent
        Intent intent = getIntent();
        if(intent.hasExtra(INTENT_EXTRA_NOTE_ID_KEY))
        {
            setTitle("Edit Note"); // if we edit a note
            editTextNoteTitle.setText(intent.getStringExtra(INTENT_EXTRA_TITLE_KEY));
            editTextNoteDescription.setText(intent.getStringExtra(INTENT_EXTRA_DESCRIPTION_KEY));
            numberPickerPriority.setValue(intent.getIntExtra(INTENT_EXTRA_PRIORITY_KEY, 1));

        }else {
            setTitle("Add Note"); // if we add a note (one way to set the action bar name alternatively through the manifest)
        }
    }


    /*
        Tell the system to replace the toolbar menu with our own menu for this activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /*
        When selecting a menu item from the appbar, identify it by id and do something
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();

                return true; // if we handle the case (we click the menu item)
            default:
                return super.onOptionsItemSelected(item); 
        }
    }

    private void saveNote() {
        String title = editTextNoteTitle.getText().toString();
        String description = editTextNoteDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "PLEASE INSERT A TITLE AND A DESCRIPTION", Toast.LENGTH_SHORT).show();
        }

        //  declare and define (instantiate) the intent that will be used to send the data
        Intent data = new Intent();

        // send the input (key-value pairs) with the bundle
        data.putExtra(INTENT_EXTRA_TITLE_KEY, title);
        data.putExtra(INTENT_EXTRA_DESCRIPTION_KEY, description);
        data.putExtra(INTENT_EXTRA_PRIORITY_KEY, priority);

        // get the id of the Room database Note entry
        int id = getIntent().getIntExtra(INTENT_EXTRA_NOTE_ID_KEY, -1);
        if(id != - 1)
        {
            data.putExtra(INTENT_EXTRA_NOTE_ID_KEY, id);
        }

        //  set result for the caller activity
        setResult(RESULT_OK, data);
        finish(); // destroy the activity as soon as the data is sent (onResume - onPause - onStop - onDestroy)
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() called");
    }


}