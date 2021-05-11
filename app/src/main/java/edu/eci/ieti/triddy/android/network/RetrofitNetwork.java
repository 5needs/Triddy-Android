package edu.eci.ieti.triddy.android.network;

import java.io.IOException;

import edu.eci.ieti.triddy.android.network.service.AuthService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork {

    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private AuthService authService;

    public RetrofitNetwork() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        authService = retrofit.create(AuthService.class);
    }

    public RetrofitNetwork(final String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain)
                    throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder().header("Accept", "application/json").header("Authorization",
                        "Bearer "
                                + token).method(
                        original.method(), original.body()).build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(
                        httpClient.build()).build();
        authService = retrofit.create(AuthService.class);
    }

    public AuthService getAuthService() {
        return authService;
    }
}
