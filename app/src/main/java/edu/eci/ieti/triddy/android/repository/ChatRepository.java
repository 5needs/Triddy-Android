package edu.eci.ieti.triddy.android.repository;

import android.app.Application;

import edu.eci.ieti.triddy.android.dao.ChatDao;
import edu.eci.ieti.triddy.android.database.ChatDatabase;
import edu.eci.ieti.triddy.android.model.Chat;
import edu.eci.ieti.triddy.android.network.RetrofitNetwork;
import edu.eci.ieti.triddy.android.network.service.ChatService;

import java.io.IOException;
import java.util.List;

public class ChatRepository {
    private ChatDao chatDao;
    private ChatService chatService;

    public ChatRepository(Application application, String token){
        ChatDatabase chatDatabase = ChatDatabase.getDatabase(application);
        chatDao = chatDatabase.chatDao();
        chatService = new RetrofitNetwork(token,1).getChatService();
    }

    public List<Chat> getChatsFromUser(String user)  {
        List<Chat> chats;
        chatDao.deletAll();
        try {
            chats = chatService.getChatsFromUser(user).execute().body();
            if (chats != null){
                for (Chat chat : chats){
                    chatDao.insert(chat);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chatDao.getChatsFromUser(user);
    }

    public Chat insert(Chat chat){
        Chat newChat = null;
        try {
            newChat = chatService.addChat(chat).execute().body();
            chatDao.insert(newChat);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newChat;
    }


}
