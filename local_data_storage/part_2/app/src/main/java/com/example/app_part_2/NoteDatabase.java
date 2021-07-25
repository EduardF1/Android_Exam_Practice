package com.example.app_part_2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/*
    @Database annotation to specify that is a RoomDatabase;
    (entities = {Note.class} - any number of entities (tables in the database),
    version = version of the database, whenever there is a change to the database, the versioning number should be changed
 */
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; // instance of the database

    public abstract NoteDao noteDao(); // the DAO (CRUD abstraction layer) reference getter (Room will autogenerate all the necessary code/ it subclasses our abstract class)

    /*
        Synchronized keyword so as to not allow more than 1 thread at a time to access the database,
        if we wouldn't have used this, say 2 threads would call this method, we would create 2
        database instances, this is something we want to avoid.

        params:
                Context context - the application context (app environment/ back stack)
                Class<T> class - the database class type
                String name - file name of the database

     */
    public static synchronized  NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database").
                    fallbackToDestructiveMigration(). // if the database does not match the versioning, destroy it and recreate it
                    addCallback(roomCallback)   // populate through the callBack on initial creation of the database
                    .build(); // build the database (create the instance)
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase roomDatabase) {
            this.noteDao = roomDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));

            return null;
        }
    }
}
