package com.example.app_part_1.ui.notes_simple.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

//  Data access object (Database placeholder at the moment), caches the data
public class NoteDao {

    private MutableLiveData<List<Note>> allNotes; // changeable data
    private static NoteDao instance; // database placeholder instance

    private NoteDao(){
        allNotes = new MutableLiveData<>();
        List<Note> newList = new ArrayList<>();
        allNotes.setValue(newList); // work on the main thread
    }

    //  Singleton for the database placeholder
    public static NoteDao getInstance(){
        if(instance == null){
            instance = new NoteDao();
        }
        return instance;
    }

    //  Immutable (it is only retrieved data)
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note){
        List<Note> currentNotes = allNotes.getValue(); // get the current notes (what is currently stored in allNotes)
        currentNotes.add(note); // add the new note
        allNotes.postValue(currentNotes); // update the notes (allNotes) to contain the new note (initially setValue - sync, changed to PostValue - async)
    }

    public void deleteAllNotes() {
        List<Note> newList = new ArrayList<>(); //  create a new empty list
        allNotes.setValue(newList); //  update the notes (all notes) to through the main thread to have the value of the newly created list
    }
}
