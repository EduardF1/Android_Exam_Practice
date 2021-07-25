package com.example.app_part_1;

//  POJO class for the object to hold the data (will be displayed in the recycler view)
public class Person {
    private String personName, occupation, address;
    private int mIconId; // filed used for the image id

    public Person(String personName, String occupation, String address, int mIconId) {
        this.personName = personName;
        this.occupation = occupation;
        this.address = address;
        this.mIconId = mIconId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getAddress() {
        return address;
    }

    public int getIconId() {
        return mIconId;
    }
}
