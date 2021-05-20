package edu.eci.ieti.triddy.android.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import edu.eci.ieti.triddy.android.network.service.AuthService;
import edu.eci.ieti.triddy.android.network.service.CalificationService;
import edu.eci.ieti.triddy.android.network.service.ChatService;
import edu.eci.ieti.triddy.android.network.service.UserService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork {

    private static final String[] BASE_URL = {"http://ec2-34-203-184-51.compute-1.amazonaws.com:8080",
                                                "http:/3.239.114.47:8080/"};
    private AuthService authService;
    private ChatService chatService;
    private CalificationService calificationService;
    private UserService userService;

    public RetrofitNetwork() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL[0])
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
                new Retrofit.Builder().baseUrl(BASE_URL[0]).addConverterFactory(GsonConverterFactory.create()).client(
                        httpClient.build()).build();
        authService = retrofit.create(AuthService.class);
        userService = retrofit.create(UserService.class);
    }

    public RetrofitNetwork( final String token, int pos )
    {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor( new Interceptor()
        {
            @Override
            public okhttp3.Response intercept( Chain chain )
                    throws IOException
            {
                Request original = chain.request();

                Request request = original.newBuilder().header( "Accept", "application/json" ).header( "Authorization",
                        "Bearer "
                                + token ).method(
                        original.method(), original.body() ).build();
                return chain.proceed( request );
            }
        } );
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl( BASE_URL[pos] ).addConverterFactory( GsonConverterFactory.create(gson) ).client(
                        httpClient.build() ).build();
        authService = retrofit.create(AuthService.class);
        chatService = retrofit.create( ChatService.class );
        calificationService = retrofit.create(CalificationService.class);
    }


    public AuthService getAuthService() {
        return authService;
    }
    public ChatService getChatService(){
        return this.chatService;
    }

    public CalificationService getCalificationService(){
        return this.calificationService;
    }

    public UserService getUserService(){
        return this.userService;
    }
}

