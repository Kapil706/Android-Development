
package com.example.kapil.beach.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SunPosition {

    @SerializedName("azimuth")
    @Expose
    private Double azimuth;
    @SerializedName("altitude")
    @Expose
    private Double altitude;

    public Double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Double azimuth) {
        this.azimuth = azimuth;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }


}
