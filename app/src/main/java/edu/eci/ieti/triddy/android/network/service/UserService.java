package edu.eci.ieti.triddy.android.network.service;

import java.util.List;

import edu.eci.ieti.triddy.android.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("/api/users/{email}")
    Call<User> getUser(@Path("email") String email);
}
