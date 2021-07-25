package com.example.app_part_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NoteViewModel noteViewModel;
    private final static int ADD_NOTE_REQUEST_CODE = 1;
    private final static int EDIT_NOTE_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Use the recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);


        /*
            instantiate the view model
            - use the System's ViewModelProvider, give it the owner (scope for the view model, here - the activity)
            - if the view model was not already created, create it, otherwise, just retrieve it.
         */
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        //  observe the the LiveData, whenever it is changed, notify (callback) and do something
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setNotesList(notes);
            }
        });

        //  Fab handling
        FloatingActionButton add_note_floatingActionButton = findViewById(R.id.floating_action_button_add);
        add_note_floatingActionButton.setOnClickListener(this);

        //  SWIPE animation (to remove a note); params: 0 - drag/drop value, directions for swipe (LEFT and RIGHT)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //  get the Note which was swiped in the adapter at its viewHolder position in the recycler view
                noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note deleted !", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView); // add the functionality to the recycler view

        //  implement the listener using an anonymous inner class
        //  this will be used to update an item from the list
        noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Note note) {
                // explicit
                Intent intent = new Intent(MainActivity.this, AddAndEditNoteActivity.class);
                intent.putExtra(AddAndEditNoteActivity.INTENT_EXTRA_NOTE_ID_KEY, note.getId());
                intent.putExtra(AddAndEditNoteActivity.INTENT_EXTRA_TITLE_KEY, note.getTitle());
                intent.putExtra(AddAndEditNoteActivity.INTENT_EXTRA_DESCRIPTION_KEY, note.getDescription());
                intent.putExtra(AddAndEditNoteActivity.INTENT_EXTRA_PRIORITY_KEY,note.getPriority());

                startActivityForResult(intent, EDIT_NOTE_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.floating_action_button_add:
            {
                Intent add_note_intent = new Intent(MainActivity.this, AddAndEditNoteActivity.class);
                startActivityForResult(add_note_intent, ADD_NOTE_REQUEST_CODE);
            }
        }
    }


    //  GET THE RESULT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST_CODE && resultCode == RESULT_OK){
            //  get the data
            String title = data.getStringExtra(AddAndEditNoteActivity.INTENT_EXTRA_TITLE_KEY);
            String description = data.getStringExtra(AddAndEditNoteActivity.INTENT_EXTRA_DESCRIPTION_KEY);
            int priority = data.getIntExtra(AddAndEditNoteActivity.INTENT_EXTRA_PRIORITY_KEY, 1);

            Note receivedNote = new Note(title, description, priority);
            noteViewModel.insert(receivedNote);

            Toast.makeText(this, "Note added !", Toast.LENGTH_SHORT).show();
        }else if(requestCode == EDIT_NOTE_REQUEST_CODE && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddAndEditNoteActivity.INTENT_EXTRA_NOTE_ID_KEY, -1);
            //  handle base/error case
            if(id == -1)
            {
                Toast.makeText(this, "Note cannot be updated !", Toast.LENGTH_SHORT).show();
                return;
            }
            //  get the data
            String title = data.getStringExtra(AddAndEditNoteActivity.INTENT_EXTRA_TITLE_KEY);
            String description = data.getStringExtra(AddAndEditNoteActivity.INTENT_EXTRA_DESCRIPTION_KEY);
            int priority = data.getIntExtra(AddAndEditNoteActivity.INTENT_EXTRA_PRIORITY_KEY, 1);

            Note note = new Note(title, description, priority);
            note.setId(id); //  needed for the update of the RoomDatabase record (our PK)
            noteViewModel.update(note);
            Toast.makeText(this, "Note updated !", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Failed to save the note !", Toast.LENGTH_SHORT).show();
        }
    }


    //  Override the menu creation options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //  Override the menu options item selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}