
package com.example.kapil.beach.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("uv")
    @Expose
    private Double uv;
    @SerializedName("uv_time")
    @Expose
    private String uvTime;
    @SerializedName("uv_max")
    @Expose
    private Double uvMax;
    @SerializedName("uv_max_time")
    @Expose
    private String uvMaxTime;
    @SerializedName("ozone")
    @Expose
    private Double ozone;
    @SerializedName("ozone_time")
    @Expose
    private String ozoneTime;
    @SerializedName("safe_exposure_time")
    @Expose
    private SafeExposureTime safeExposureTime;
    @SerializedName("sun_info")
    @Expose
    private SunInfo sunInfo;

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

    public String getUvTime() {
        return uvTime;
    }

    public void setUvTime(String uvTime) {
        this.uvTime = uvTime;
    }

    public Double getUvMax() {
        return uvMax;
    }

    public void setUvMax(Double uvMax) {
        this.uvMax = uvMax;
    }

    public String getUvMaxTime() {
        return uvMaxTime;
    }

    public void setUvMaxTime(String uvMaxTime) {
        this.uvMaxTime = uvMaxTime;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public String getOzoneTime() {
        return ozoneTime;
    }

    public void setOzoneTime(String ozoneTime) {
        this.ozoneTime = ozoneTime;
    }

    public SafeExposureTime getSafeExposureTime() {
        return safeExposureTime;
    }

    public void setSafeExposureTime(SafeExposureTime safeExposureTime) {
        this.safeExposureTime = safeExposureTime;
    }

    public SunInfo getSunInfo() {
        return sunInfo;
    }

    public void setSunInfo(SunInfo sunInfo) {
        this.sunInfo = sunInfo;
    }


}
