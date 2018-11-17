
package com.example.kapil.beach.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SunInfo {

    @SerializedName("sun_times")
    @Expose
    private SunTimes sunTimes;
    @SerializedName("sun_position")
    @Expose
    private SunPosition sunPosition;

    public SunTimes getSunTimes() {
        return sunTimes;
    }

    public void setSunTimes(SunTimes sunTimes) {
        this.sunTimes = sunTimes;
    }

    public SunPosition getSunPosition() {
        return sunPosition;
    }

    public void setSunPosition(SunPosition sunPosition) {
        this.sunPosition = sunPosition;
    }


}
