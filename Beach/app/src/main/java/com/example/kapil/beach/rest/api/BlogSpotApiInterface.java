package com.example.kapil.beach.rest.api;

import com.example.kapil.beach.rest.model.Item;
import com.example.kapil.beach.rest.model.PostList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BlogSpotApiInterface {
    @GET("blogs/9103472313335469262/posts/")
    Call<PostList> getBlog(@Query("key") String apiKey);
    @GET("{postId}/")
    Call<Item> getBlogPost(@Query("key") String apiKey, @Path("postId") String postId);
}
