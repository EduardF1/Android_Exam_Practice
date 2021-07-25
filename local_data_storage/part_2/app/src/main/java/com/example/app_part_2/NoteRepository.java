package com.example.app_part_2;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao; // the data access object interface reference (to interact with the room database)
    private LiveData<List<Note>> allNotes; // private variable (observable) to be updated by data changes


    /*
      The constructor needed to interact with the repository from the view model
      params: Application application : as the ViewModel will have a reference to the
      repository, the application needs to be passed (Application is a subclass of
      Context)
     */
    public NoteRepository(Application application)
    {

        /*
            Member variable (initialized) with the NoteDatabase instance assigned to it
            (use application instead of Context as will be working on the background thread
            and the UI is running on the main thread). Interactions with the room database
            will be done through the DAO, however the
         */
        NoteDatabase database = NoteDatabase.getInstance(application);

        /*
            Even though .noteDao() is an abstract method, Room will generate all the
            necessary member variables and methods for it
         */
        noteDao = database.noteDao();

        /*
            Get all the notes from the DAO, Room will handle all the necessary member variables/method creation
            After getting them, store them in our list, as we are using LiveData<>, changes are being observed
            continuously.
         */
        allNotes = noteDao.getAllNotesInDescPriority();
    }


    /*

        *************************************************************************************
        *   API EXPOSED BY THE REPOSITORY TO THE OUTSIDE WORLD (WILL BE USED BY THE VM)
        *************************************************************************************
        -   The Abstraction layer of data processing from the repository to the Room database through the DAO
        -   The following set of methods requires explicit definition and later declaration as
        these will be the methods used to perform C.U.D operations on the data. R. is handled
        by using LiveData<>.
        -   Need to be used with AsyncTask<> to allow work on the background thread (this is a case in which Room does not handle the operations automatically)
     */
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    //  automatically done on the background thread by Room
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }


    /*
        *******************************************************************************************
        *   INSERT A NOTE ASYNCHRONOUSLY ON THE BACKGROUND THREAD
        * *****************************************************************************************
        inner class, static to not have a reference to the repository itself (cannot access member variables in the repository base class)
     */
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>
    {
        private NoteDao noteDao; // will be passed to the constructor of the InsertNoteAsyncTask constructor

        private InsertNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao = noteDao; // assign the passed noteDao object to the field variable
        }

        //  mandatory method to be overwritten
        @Override
        protected Void doInBackground(Note... notes) // parameter varargs (similar to an array) of given parameters
        {
            noteDao.insert(notes[0]); // access the notes[0]
            return null;
        }
    }


    /*
       *******************************************************************************************
       *   DELETE A NOTE ASYNCHRONOUSLY ON THE BACKGROUND THREAD
       * *****************************************************************************************
       inner class, static to not have a reference to the repository itself (cannot access member variables in the repository base class)
    */
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>
    {
        private NoteDao noteDao; // will be passed to the constructor of the DeleteNoteAsyncTask constructor

        private DeleteNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao = noteDao; // assign the passed noteDao object to the field variable
        }

        //  mandatory method to be overwritten
        @Override
        protected Void doInBackground(Note... notes) // parameter varargs (similar to an array) of given parameters
        {
            noteDao.delete(notes[0]); // access the notes[0]
            return null;
        }
    }

    /*
    *******************************************************************************************
    *   UPDATE A NOTE ASYNCHRONOUSLY ON THE BACKGROUND THREAD
    * *****************************************************************************************
    inner class, static to not have a reference to the repository itself (cannot access member variables in the repository base class)
 */
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>
    {
        private NoteDao noteDao; // will be passed to the constructor of the DeleteNoteAsyncTask constructor

        private UpdateNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao = noteDao; // assign the passed noteDao object to the field variable
        }

        //  mandatory method to be overwritten
        @Override
        protected Void doInBackground(Note... notes) // parameter varargs (similar to an array) of given parameters
        {
            noteDao.update(notes[0]); // access the notes[0]
            return null;
        }
    }

    /*
    *******************************************************************************************
    *   DELETE ALL NOTES ASYNCHRONOUSLY ON THE BACKGROUND THREAD
    * *****************************************************************************************
    inner class, static to not have a reference to the repository itself (cannot access member variables in the repository base class)
 */
    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private NoteDao noteDao; // will be passed to the constructor of the DeleteNoteAsyncTask constructor

        private DeleteAllNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao = noteDao; // assign the passed noteDao object to the field variable
        }

        //  mandatory method to be overwritten
        @Override
        protected Void doInBackground(Void... voids) // parameter varargs (similar to an array) of given parameters
        {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
