package com.example.kapil.beach.rest.api;

import com.example.kapil.beach.rest.model.Ultraviolet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("uv")
    Call<Ultraviolet> getUvData(@Header("x-access-token") String apiKey, @Query("lat") String lat, @Query("lng") String lon);
}
