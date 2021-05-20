package edu.eci.ieti.triddy.android.network.service;

import edu.eci.ieti.triddy.android.model.Calification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CalificationService {
    @POST("/api/califications")
    Call<Calification> addCalification(@Body Calification calification);

    @GET("/api/califications/product/{productId}")
    Call<List<Calification>> getCalificationsFromProduct(@Path("productId") String productId);

    @GET("/api/califications/user/{userId}")
    Call<List<Calification>> getCalificationsFromUser(@Path("uderId") String userId);

}
