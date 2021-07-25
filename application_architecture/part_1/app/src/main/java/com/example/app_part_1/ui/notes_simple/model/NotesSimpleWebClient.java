package com.example.app_part_1.ui.notes_simple.model;

import androidx.lifecycle.MutableLiveData;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class NotesSimpleWebClient {

    //  Changeable
    private MutableLiveData<Note> note;
    //  Web client instance
    private static NotesSimpleWebClient instance;

    //  NotesSimpleWebClient singleton
    public static NotesSimpleWebClient getInstance(){
        if(instance == null){
            instance = new NotesSimpleWebClient();
        }
        return instance;
    }

    //  private constructor
    private NotesSimpleWebClient(){
        note = new MutableLiveData<>(); // initialize the note as changeable data
        note.setValue(new Note("Note from the internet !")); // modify the data
    }

    //  method to get a note from the web, takes a listener as an argument
    public void getNote(final OnWebNoteReceivedListener listener){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                listener.onWebNoteReceived(note.getValue()); // observe when a note is received (changes in the data)
            }
        }, 5000);
    }
}
