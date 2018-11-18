package com.example.kapil.beach;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.kapil.beach.rest.api.ApiClient;
import com.example.kapil.beach.rest.api.ApiInterface;
import com.example.kapil.beach.rest.api.BlogSpotApiClient;
import com.example.kapil.beach.rest.api.BlogSpotApiInterface;
import com.example.kapil.beach.rest.model.Item;
import com.example.kapil.beach.rest.model.PostList;
import com.example.kapil.beach.rest.model.SafeExposureTime;
import com.example.kapil.beach.rest.model.Ultraviolet;
import com.google.android.gms.location.places.Place;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class Repository {
    private static final Repository INSTANCE = new Repository();
    private Place place;
    private Ultraviolet uv;
    private int skinType;

    static Repository getInstance() {
        return INSTANCE;
    }

    private Repository() {
    }

    void setPlace(Place place) {
        this.place = place;
    }

    void evaluate(final UVDataListener uvDataListener) {
        if (place == null) {
            uvDataListener.onError("Please select a beach first");
            return;
        }
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getUvData("f2b3c96338de205969efb6cea656d854",
                String.valueOf(place.getLatLng().latitude),
                String.valueOf(place.getLatLng().longitude))
        .enqueue(new Callback<Ultraviolet>() {
            @Override
            public void onResponse(@NonNull Call<Ultraviolet> call, @NonNull Response<Ultraviolet> response) {
                if (response.isSuccessful()) {
                    uv = response.body();
                    uvDataListener.onUVDataReceived();
                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        uvDataListener.onError(jObjError.getString("message"));
                    } catch (Exception e) {
                        uvDataListener.onError(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Ultraviolet> call, @NonNull Throwable t) {
                uvDataListener.onError("Failed to connect to server.");
            }
        });
    }

    Ultraviolet getUVReading() {
        return uv;
    }

    void saveSkinType(int skinType) {
        this.skinType = skinType;
    }

    int getSkinType() {
        return skinType;
    }

    int calculateSafeExposureTime() {
        SafeExposureTime safeExposureTime = uv.getResult().getSafeExposureTime();
        if (safeExposureTime != null) {
            switch (skinType) {
                case 1: return uv.getResult().getSafeExposureTime().getSt1();
                case 2: return uv.getResult().getSafeExposureTime().getSt2();
                case 3: return uv.getResult().getSafeExposureTime().getSt3();
                case 4: return uv.getResult().getSafeExposureTime().getSt4();
                case 5: return uv.getResult().getSafeExposureTime().getSt5();
                case 6: return uv.getResult().getSafeExposureTime().getSt6();
                default: return -2;
            }
        }
        else {
            return -1;
        }
    }

    String getMaxUvTime() {
        String time = uv.getResult().getUvMaxTime();
        time = time.substring(time.indexOf("T") + 1);
        time = time.substring(0, time.indexOf("Z"));
        time = time.substring(0, time.lastIndexOf(":"));
        return time;
    }

    Double getMaxUvValue() {
        return uv.getResult().getUvMax();
    }

    Place getPlace() {
        return place;
    }

    void fetchBlogsList(final FetchBlogListListener fetchBlogListListener) {
        BlogSpotApiInterface blogSpotApiInterface = BlogSpotApiClient.getClient().create(BlogSpotApiInterface.class);
        blogSpotApiInterface.getBlog("AIzaSyDfwaMdyyuFe_7NRA_n4T-KuBacOazuD6g")
                .enqueue(new Callback<PostList>() {
                    @Override
                    public void onResponse(@NonNull Call<PostList> call, @NonNull Response<PostList> response) {
                        Log.d("response", response.body().toString());
                        fetchBlogListListener.onListFetched(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<PostList> call, @NonNull Throwable t) {

                    }
                });
    }

    void fetchBlog(String postId) {
        BlogSpotApiInterface blogSpotApiInterface = BlogSpotApiClient.getClient().create(BlogSpotApiInterface.class);
        blogSpotApiInterface.getBlogPost("AIzaSyDfwaMdyyuFe_7NRA_n4T-KuBacOazuD6g", postId)
                .enqueue(new Callback<Item>() {
                    @Override
                    public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                        Log.d("response", response.body().toString());
                    }

                    @Override
                    public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {

                    }
                });
    }

    interface UVDataListener {
        void onUVDataReceived();
        void onError(String error);
    }

    interface FetchBlogListListener {
        void onListFetched(PostList postLists);
        void onError(String error);
    }
}
