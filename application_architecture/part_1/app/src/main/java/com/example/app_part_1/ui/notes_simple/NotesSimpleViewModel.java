package com.example.app_part_1.ui.notes_simple;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_part_1.ui.notes_simple.model.Note;
import com.example.app_part_1.ui.notes_simple.model.NotesSimpleRepository;
import com.example.app_part_1.ui.notes_simple.model.OnWebNoteReceivedListener;

import java.util.ArrayList;
import java.util.List;


public class NotesSimpleViewModel extends ViewModel implements OnWebNoteReceivedListener {

    //  reference to the repository ("single source of truth")
    //  will be delegated by the View Model
    private NotesSimpleRepository repository;

    //  flag boolean variable to check note from web status
    private MutableLiveData<Boolean> isLoading;

    public NotesSimpleViewModel(){
        repository = repository.getInstance(); // should always be the same (single instance)

        isLoading = new MutableLiveData<>(); // initialize with new empty changeable data
        isLoading.setValue(false); // set the empty changeable data to false
    }

    public LiveData<List<Note>> getAllNotes(){
        return repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    //  delegate call to the repository
    public void getNoteFromWeb() {
        isLoading.setValue(true); // As we are getting data from the web and this is done with a delay of 5 s, set the changeable data to true
        repository.addNoteFromWeb(this); // pass the repository as a listener for the web note
    }

    public LiveData<Boolean> getIsLoading(){
        return isLoading; // retrieve the value (true/false) of the isLoading flag
    }

    @Override
    public void onWebNoteReceived(Note note){
        repository.insert(note); // sync will execute first, registered call to the main thread

        /*
            1) async, will execute second, used so as to not crash the app as we already have a call on the main thread
            2) as the note was received, set the flag to false
         */
        isLoading.postValue(false);
    }





    /*
            V 1 - NO REPOSITORY (functionality delegated to the ViewModel entirely  (mock the repository)

             private MutableLiveData<List<String>> notes; // Subclass of LiveData (Inheritance), setValue() is public, we can change the value of the observed data
    private LiveData<Boolean> isSaving; //  immutable (cannot be modified)

    public NotesSimpleViewModel() {
        notes = new MutableLiveData<>();
        List<String> newList = new ArrayList<>();
        notes.setValue(newList);
    }


    public LiveData<List<String>> getAllNotes(){
        return notes;
    }

    public void addNote(String note){
        List<String> currentNotes = notes.getValue();
        currentNotes.add(note);
        notes.setValue(currentNotes);   //  change the value of the MutableLiveData and notify the observers
    }

    public void deleteAllNotes(){
        List<String> currentNotes = notes.getValue(); // returns any data wrapped in LiveData
        currentNotes.clear();
        notes.setValue(currentNotes);
    }
     */

}