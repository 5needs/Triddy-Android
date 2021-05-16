package edu.eci.ieti.triddy.android.network.service;

import edu.eci.ieti.triddy.android.network.data.LoginWrapper;
import edu.eci.ieti.triddy.android.network.data.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService
{

    @POST( "login" )
    Call<Token> login(@Body LoginWrapper loginWrapper );

}
