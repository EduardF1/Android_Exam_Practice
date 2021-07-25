package com.example.app_part_1.ui.notes_simple;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_part_1.R;
import com.example.app_part_1.ui.notes_simple.model.Note;

import java.util.List;

public class NotesSimpleFragment extends Fragment implements View.OnClickListener {

    private NotesSimpleViewModel notesSimpleViewModel;
    TextView textViewNote;
    EditText editText;
    Button button_add_note, button_delete_all, button_add_note_from_web;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notesSimpleViewModel =
                new ViewModelProvider(this).get(NotesSimpleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_simple_notes, container, false);


        //  find the view resources
        textViewNote = root.findViewById(R.id.textViewNote);
        editText = root.findViewById(R.id.addNoteEditText);
        button_add_note = root.findViewById(R.id.button_add_note);
        button_delete_all = root.findViewById(R.id.button_delete_all);
        button_add_note_from_web = root.findViewById(R.id.button_note_from_web); // simulate getting a note from the web
        progressBar = root.findViewById(R.id.progressBar);


        //  set listeners
        button_add_note.setOnClickListener(this);
        button_delete_all.setOnClickListener(this);
        button_add_note_from_web.setOnClickListener(this);


        //  observe changes in the note list
        notesSimpleViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if(!notes.isEmpty())
                {
                    textViewNote.setText("");
                    for (Note note : notes)
                    {
                        textViewNote.append(note.getTitle() + "\n");
                    }
                }
                else {
                    textViewNote.setText("Empty");
                }

            }
        });

        //  observe changes of the progress bar flag
        notesSimpleViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if(loading){
                    progressBar.setVisibility(View.VISIBLE); // if we are loading a note from the web, show the progress bar
                }else {
                    progressBar.setVisibility(View.INVISIBLE); // if we are not, hide it
                }
            }
        });

        return root;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.button_add_note):
            {
             notesSimpleViewModel.insert(new Note(editText.getText().toString()));
                Log.i("NotesSimpleFragment", "NoteDao added !");
                break;
            }
            case (R.id.button_delete_all):
            {
                notesSimpleViewModel.deleteAllNotes();
                break;
            }
            case (R.id.button_note_from_web):
            {
                notesSimpleViewModel.getNoteFromWeb(); // delegate to VM
                break;
            }
        }
    }
}