package com.example.app_part_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gson gson = new Gson(); cannot be used with expose annotations for serialization

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Employee employee = new Employee("John", 30, "john@gmail.com", "randomPassword");

        String jsonResult = gson.toJson(employee);


        String jsonString = "{\"firstName\":\"John\",\"age\":30,\"mail\":\"john@gmail.com\",\"password\":\"randomPassword\"}";

        Employee employee1 = gson.fromJson(jsonString, Employee.class);
    }
}