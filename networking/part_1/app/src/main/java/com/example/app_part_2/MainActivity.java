package com.example.app_part_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  declare and initialize a new instance of a Gson object, used to serialize - java objects to json /deserialize json to java objects
        Gson gson = new Gson();

        //   Example of how to serialize a java object to json through the Gson object
        /*
        Address address = new Address("Germany", "Hanover");
        List<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("Wife", 39));
        family.add(new FamilyMember("Daughter", 5));

        Employee employee = new Employee("Karl", 20, "Karl@mail.com", address, family);
        String json = gson.toJson(employee);
         */


        // Serialize only the family ArrayList
        /*
        List<FamilyMember> family = new ArrayList<>();
        family.add(new FamilyMember("Wife", 39));
        family.add(new FamilyMember("Daughter", 5));
        String json = gson.toJson(family);
         */



        //  Deserialize the familyJson to an array
        /*
            String familyJson = "[{\n" +
                "            \"age\":39, \"role\":\"Wife\"\n" +
                "        },{\n" +
                "            \"age\":5, \"role\":\"Daughter\"\n" +
                "        }]";

        FamilyMember[] family = gson.fromJson(familyJson, FamilyMember[].class);
         */


        //  Deserialize the familyJson to an ArrayList (this is a special case, it requires a Type)
        String familyJson = "[{\n" +
                "            \"age\":39, \"role\":\"Wife\"\n" +
                "        },{\n" +
                "            \"age\":5, \"role\":\"Daughter\"\n" +
                "        }]";

        Type familyType = new TypeToken<ArrayList<FamilyMember>>(){}.getType();
        ArrayList<FamilyMember> family = gson.fromJson(familyJson, familyType);



        //  Example of how to deserialize a json object to a java object through the Gson object
        //  Note: if any of the attributes is not declared within the json as a match to the serialized name annotation, it will be set to null
     /*

    String json = "{\n" +
                "  \"address\": {\n" +
                "    \"city\": \"Hanover\",\n" +
                "    \"country\": \"Germany\"\n" +
                "  },\n" +
                "  \"age\": 20,\n" +
                "  \"family\": [\n" +
                "    {\n" +
                "      \"age\": 39,\n" +
                "      \"role\": \"Wife\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"age\": 5,\n" +
                "      \"role\": \"Daughter\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"first_name\": \"Karl\",\n" +
                "  \"mail\": \"Karl@mail.com\"\n" +
                "}";

        //  fromJson(jsonFileName - json file to deserialize, targetClass - class type to which to deserialize to)
        Employee employee = gson.fromJson(json, Employee.class);

      */


    }
}