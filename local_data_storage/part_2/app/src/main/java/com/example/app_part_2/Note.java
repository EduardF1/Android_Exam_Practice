package com.example.app_part_2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
    Concept: Entity object that will represent the table created with the Room library
 */

@Entity(tableName = "note_table") // annotation for the table name, at compile time, this will create a table for the defined entity object in a sqlite database
public class Note {

    // member variables (will represent table columns/attributes/fields)
    @PrimaryKey(autoGenerate = true) // autogenerate id (increment continuously, each new row will have a new id starting from 0), used to uniquely identify each table record
    private int id;
    private String title;
    private String description;
    private int priority;

    //  Constructor for the entity, it represents 1 table record, the id is omitted as it will be automatically initialized (starting from 0)
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //  setter for the id (required by Room to recreate the row). We use only have a setter for the id as we are not having it in the constructor
    public void setId(int id) {
        this.id = id;
    }

    //  getters required to retrieve table row/record data (persist data)

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
/*
    Note:

    By default all the columns have the same name as the member fields of the entity, however, we
    can modify this by using for example : @ColumnInfo(name = "priority_column")
 */