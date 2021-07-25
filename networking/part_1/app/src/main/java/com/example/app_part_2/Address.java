package com.example.app_part_2;

import com.google.gson.annotations.SerializedName;

//  Class designed for being a member field of the Employee class
public class Address {

    @SerializedName("country")
    private String mCountry;
    @SerializedName("city")
    private String mCity;

    //  Constructor to initialize the fields
    public Address(String mCountry, String mCity) {
        this.mCountry = mCountry;
        this.mCity = mCity;
    }
}
