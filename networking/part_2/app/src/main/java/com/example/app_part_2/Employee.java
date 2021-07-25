package com.example.app_part_2;


import com.google.gson.annotations.Expose;

public class Employee {

    //  Expose annotation to specify that these member fields are to be exposed (available for serialization/deserialization)
    @Expose // default value set to true for both serialize/deserialize
    private String firstName;
    @Expose (serialize = false) // set deserialization to false
    private int age;
    @Expose (deserialize = false) // set serialization to false
    private String mail;

    private String password; // transient = keyword to exclude password from serialization or @Expose (serialize = false, deserialize = false)

    public Employee(String firstName, int age, String mail, String password) {
        this.firstName = firstName;
        this.age = age;
        this.mail = mail;
        this.password = password;
    }
}
