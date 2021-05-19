package edu.eci.ieti.triddy.android.network.service;

import edu.eci.ieti.triddy.android.model.Chat;
import edu.eci.ieti.triddy.android.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChatService {
    @GET("/api/chat/user/{userId}")
    Call<List<Chat>> getChatsFromUser(@Path("userId") String userId);

    @GET("/api/chat/messages/{chatId}")
    Call<List<Message>> getMessagesOfChat(@Path(("chatId")) String chatId);

    @POST("/api/chat")
    Call<Chat> addChat(@Body Chat chat);

    @POST("/api/chat/messages")
    Call<Message> addMessage(@Body Message message);

}
