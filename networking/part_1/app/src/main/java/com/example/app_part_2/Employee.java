package com.example.app_part_2;


import android.provider.Telephony;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee {

    /*
        Annotations used as the json keys do not match the attributes/field members of the Employee class
        Note: without these annotations, serialization/deserialization would not be possible
     */

    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("age")
    private  int mAge;
    @SerializedName("mail")
    private String mMail;
    @SerializedName("address")
    private Address mAddress; // object member field

    @SerializedName("family")
    private List<FamilyMember> mFamily; // same output if we had an array

    public Employee(String firstName, int age, String mail, Address address, List<FamilyMember> family) {
        mFirstName = firstName;
        mAge = age;
        mMail = mail;
        mAddress = address;
        mFamily = family;
    }
}
