package com.example.app_part_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PersonsAdapter.OnListItemClickListener, AdapterView.OnItemSelectedListener {

    RecyclerView myPersonsRecyclerViewList;
    PersonsAdapter myPersonsAdapter;
    ArrayList<Person> personsDataSource = new ArrayList<>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provideData();

        myPersonsRecyclerViewList = findViewById(R.id.recyclerView);
        myPersonsRecyclerViewList.hasFixedSize(); // for performance enhancement

        //  set a layout manager for the recycler view (how should the list View elements be displayed)
        //  param: a new LinearLayoutManager taking the activity's context as an argument
        myPersonsRecyclerViewList.setLayoutManager(new LinearLayoutManager(this));


        // (a) Adapter responsibilities: create new items, populate the items with data, return the information (b) give it data
        //  (c) give it the activity as a listener as an argument
        myPersonsAdapter = new PersonsAdapter(personsDataSource, this);
        myPersonsRecyclerViewList.setAdapter(myPersonsAdapter); // attach the adapter to the RecyclerView

        TextView textView = findViewById(R.id.personCountTextView);
        textView.setText(String.valueOf(myPersonsAdapter.getItemCount()));

        //  Spinner
        spinner = findViewById(R.id.my_spinner);

        //  Create an Array adapter (for the array of strings provided)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_key_persons, android.R.layout.simple_spinner_item);
        //  Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //  Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //  Implement the listener and pass this as the interface instance
        spinner.setOnItemSelectedListener(this);
    }

    //  Item selected from the dropdown
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        parent.getItemAtPosition(pos);  //  get selected item
    }

    //  Nothing selected
    public void onNothingSelected(AdapterView<?> parent){

    }




    private void provideData() {
        personsDataSource.add(new Person("John", "Wayne", "Street no.3", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Laura", "McArthur", "Long Island", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Martin", "Rooney", "Madrid Square", R.drawable.ic_person_foreground));

        personsDataSource.add(new Person("Nicholas", "Karlson", "Oklahoma", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Marianne", "Simpson", "Rigid Street", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Daniel", "De Bruyne", "Winner valley", R.drawable.ic_person_foreground));

        personsDataSource.add(new Person("Frederick", "Mauritsen", "Kenwood alley", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Osama", "Danielsen", "Central Park", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Lucas", "Knop", "Major Road", R.drawable.ic_person_foreground));

        personsDataSource.add(new Person("Michael", "Szava", "Victory Street", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Tony", "Naverson", "Great hall", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Gregor", "Erickson", "Mambo no.122", R.drawable.ic_person_foreground));
        personsDataSource.add(new Person("Matthew", "Johnson", "Street no.3", R.drawable.ic_person_foreground));

    }

    public void onListItemClick(int clickedItemIndex){
        int personNumber = clickedItemIndex + 1; // start at 0
        Person person = personsDataSource.get(clickedItemIndex);
        Toast.makeText(this, "Entry clicked: " + personNumber + "\n" +
                "Person name: " + person.getPersonName(), Toast.LENGTH_SHORT).show();

    }
}