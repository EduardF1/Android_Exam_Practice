package com.example.app_part_1.ui.notes_simple.model;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesSimpleRepository {

    private NotesSimpleWebClient notesSimpleWebClient; // web client mock/placeholder
    private NoteDao noteDao; // reference to the data access object
    private static NotesSimpleRepository instance; // reference to the single instance of the repository

    //  Constructor using the Data Access Object
    public NotesSimpleRepository() {
        noteDao = noteDao.getInstance(); // for now, mock of DAO that will be later used with Room
        notesSimpleWebClient = NotesSimpleWebClient.getInstance(); // for now, mock the web client, later used with Retrofit
    }

    //  getter for the single instance of the repository
    public static NotesSimpleRepository getInstance(){
        if(instance == null){
            instance = new NotesSimpleRepository();
        }
        return instance;
    }

    public LiveData<List<Note>> getAllNotes(){
        return noteDao.getAllNotes(); // delegate the call further to the data access object to retrieve all notes
    }

    public void insert(Note note){
        noteDao.insert(note); // delegate the call further to the data access object to insert a new note
    }

    public void deleteAllNotes(){
        noteDao.deleteAllNotes(); // delegate the call further to the data access object to delete all the notes
    }

    //  Mock receiving a note from a web service
    public void addNoteFromWeb(OnWebNoteReceivedListener listener) {
        notesSimpleWebClient.getNote(listener); // observe when a note is received
    }
}
