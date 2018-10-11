package com.example.kapil.explicitintents2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

//public class Person implements Serializable
public class Person implements Parcelable {

    private  String firstName;
    private String lastName;
    private String qualification;

    private int height;

    private GENDER gender;

    enum GENDER {MALE,FEMALE};

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public boolean isHostelPreference() {
        return hostelPreference;
    }

    public void setHostelPreference(boolean hostelPreference) {
        this.hostelPreference = hostelPreference;
    }

    public List<City> getResidencyHistory() {
        return residencyHistory;
    }

    public void setResidencyHistory(List<City> residencyHistory) {
        this.residencyHistory = residencyHistory;
    }

    private List<String> qualifications;

    private boolean hostelPreference;

    private List<City> residencyHistory;


    public  Person(){
        super();
    }
    public Person(Parcel parcel){

        this.firstName = parcel.readString();
        this.lastName =parcel.readString();
        this.qualification=parcel.readString();
        this.height = parcel.readInt();
        this.qualifications =parcel.readArrayList(null);
        this.gender =(parcel.readByte()==1?GENDER.MALE:GENDER.FEMALE);
        this.hostelPreference=parcel.readByte()==1?true:false;
        this.residencyHistory=parcel.createTypedArrayList(City.CREATOR);


    }
   public static final Creator<Person> CREATOR = new Creator<Person>(){


       @Override
       public Person createFromParcel(Parcel parcel) {
           return new Person(parcel);
       }

       @Override
       public Person[] newArray(int i) {
           return new Person[i];
       }
   };

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getQualification() {
        return qualification;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.qualification);
        parcel.writeInt(this.height);
        parcel.writeList(this.qualifications);
        parcel.writeByte(this.gender==GENDER.MALE?(byte)1:(byte)0);
        parcel.writeByte(this.hostelPreference==true?(byte)1:(byte)0);
        parcel.writeTypedList(this.residencyHistory);

    }

}
