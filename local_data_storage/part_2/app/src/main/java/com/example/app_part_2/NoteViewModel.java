package com.example.app_part_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*
    ViewModels survive configuration changes (ex.: screen rotation, language change etc.)
 */

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    //  we extend AndroidViewModel to pass the application (subclass of context) instead of context
    //
    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository = new NoteRepository(application); // pass the application all the way to the RoomDatabase to create the instance
        allNotes = repository.getAllNotes(); // cache the notes
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }


    public void update(Note note) {
        repository.update(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
