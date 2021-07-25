package com.example.app_part_2;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Concept: Dao = data access object (should be an interface or abstract class)
// Note: at compile time, we will get an error if the complex queries do not match the table column names

@Dao // By using this annotation, let the Room library know that this is our DAO (interface to interact with the database - perform CRUD)
public interface NoteDao {

    @Insert //  simple operation annotation (we only define the operation, Room will generate all the necessary code (define the method)
    void insert(Note note);

    @Update //  simple operation annotation (we only define the operation, Room will generate all the necessary code (define the method)
    void update(Note note);

    @Delete //  simple operation annotation (we only define the operation, Room will generate all the necessary code (define the method)
    void delete(Note note);


    @Query("DELETE FROM note_table")   //  complex operation annotation (these use the @Query annotation), the SQL language is T-SQL (we only define the operation, Room will generate all the necessary code (define the method)
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC") //  complex operation annotation (these use the @Query annotation), the SQL language is T-SQL (we only define the operation, Room will generate all the necessary code (define the method)
    LiveData<List<Note>> getAllNotesInDescPriority();   //  the select return query results will be mapped to the list of notes which is observable by being wrapped in LiveData<>
}
