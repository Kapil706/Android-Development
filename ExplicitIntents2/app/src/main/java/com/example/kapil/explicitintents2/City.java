package com.example.kapil.explicitintents2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class City implements Parcelable{









    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String CityName;

    private Date date;

    public City(){
        super();

    }
    public City(Parcel parcel){
        this.CityName=parcel.readString();
        this.date =new Date(parcel.readLong());
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
              parcel.writeString(this.CityName);
              parcel.writeLong(this.date.getTime());

    }
    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel parcel) {
            return new City(parcel);
        }

        @Override
        public City[] newArray(int i) {
            return new City[i];
        }
    };
}
