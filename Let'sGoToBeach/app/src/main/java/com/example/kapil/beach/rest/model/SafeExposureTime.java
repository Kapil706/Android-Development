
package com.example.kapil.beach.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeExposureTime {

    @SerializedName("st1")
    @Expose
    private Integer st1;
    @SerializedName("st2")
    @Expose
    private Integer st2;
    @SerializedName("st3")
    @Expose
    private Integer st3;
    @SerializedName("st4")
    @Expose
    private Integer st4;
    @SerializedName("st5")
    @Expose
    private Integer st5;
    @SerializedName("st6")
    @Expose
    private Integer st6;

    public Integer getSt1() {
        return st1;
    }

    public void setSt1(Integer st1) {
        this.st1 = st1;
    }

    public Integer getSt2() {
        return st2;
    }

    public void setSt2(Integer st2) {
        this.st2 = st2;
    }

    public Integer getSt3() {
        return st3;
    }

    public void setSt3(Integer st3) {
        this.st3 = st3;
    }

    public Integer getSt4() {
        return st4;
    }

    public void setSt4(Integer st4) {
        this.st4 = st4;
    }

    public Integer getSt5() {
        return st5;
    }

    public void setSt5(Integer st5) {
        this.st5 = st5;
    }

    public Integer getSt6() {
        return st6;
    }

    public void setSt6(Integer st6) {
        this.st6 = st6;
    }


}
