package com.example.kapil.recyclerview;

public class Person {

    private String name;
    private String lastname;
    private GENDER gender;
    private String nationality;

    enum GENDER {MALE,FEMALE};

    public Person(){
        super();
    }


    public Person(String name, String lastname, GENDER gender, String nationality) {
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public GENDER getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
