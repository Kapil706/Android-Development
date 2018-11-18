
package com.example.kapil.beach.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SunTimes {

    @SerializedName("solarNoon")
    @Expose
    private String solarNoon;
    @SerializedName("nadir")
    @Expose
    private String nadir;
    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    @SerializedName("sunriseEnd")
    @Expose
    private String sunriseEnd;
    @SerializedName("sunsetStart")
    @Expose
    private String sunsetStart;
    @SerializedName("dawn")
    @Expose
    private String dawn;
    @SerializedName("dusk")
    @Expose
    private String dusk;
    @SerializedName("nauticalDawn")
    @Expose
    private String nauticalDawn;
    @SerializedName("nauticalDusk")
    @Expose
    private String nauticalDusk;
    @SerializedName("nightEnd")
    @Expose
    private String nightEnd;
    @SerializedName("night")
    @Expose
    private String night;
    @SerializedName("goldenHourEnd")
    @Expose
    private String goldenHourEnd;
    @SerializedName("goldenHour")
    @Expose
    private String goldenHour;

    public String getSolarNoon() {
        return solarNoon;
    }

    public void setSolarNoon(String solarNoon) {
        this.solarNoon = solarNoon;
    }

    public String getNadir() {
        return nadir;
    }

    public void setNadir(String nadir) {
        this.nadir = nadir;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunriseEnd() {
        return sunriseEnd;
    }

    public void setSunriseEnd(String sunriseEnd) {
        this.sunriseEnd = sunriseEnd;
    }

    public String getSunsetStart() {
        return sunsetStart;
    }

    public void setSunsetStart(String sunsetStart) {
        this.sunsetStart = sunsetStart;
    }

    public String getDawn() {
        return dawn;
    }

    public void setDawn(String dawn) {
        this.dawn = dawn;
    }

    public String getDusk() {
        return dusk;
    }

    public void setDusk(String dusk) {
        this.dusk = dusk;
    }

    public String getNauticalDawn() {
        return nauticalDawn;
    }

    public void setNauticalDawn(String nauticalDawn) {
        this.nauticalDawn = nauticalDawn;
    }

    public String getNauticalDusk() {
        return nauticalDusk;
    }

    public void setNauticalDusk(String nauticalDusk) {
        this.nauticalDusk = nauticalDusk;
    }

    public String getNightEnd() {
        return nightEnd;
    }

    public void setNightEnd(String nightEnd) {
        this.nightEnd = nightEnd;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getGoldenHourEnd() {
        return goldenHourEnd;
    }

    public void setGoldenHourEnd(String goldenHourEnd) {
        this.goldenHourEnd = goldenHourEnd;
    }

    public String getGoldenHour() {
        return goldenHour;
    }

    public void setGoldenHour(String goldenHour) {
        this.goldenHour = goldenHour;
    }

}
