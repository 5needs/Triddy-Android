package edu.eci.ieti.triddy.android.repository;

import android.app.Application;

import edu.eci.ieti.triddy.android.dao.MessageDao;
import edu.eci.ieti.triddy.android.database.ChatDatabase;
import edu.eci.ieti.triddy.android.model.Message;
import edu.eci.ieti.triddy.android.network.RetrofitNetwork;
import edu.eci.ieti.triddy.android.network.service.ChatService;

import java.io.IOException;
import java.util.List;

public class MessageRepository {
    private MessageDao messageDao;
    private ChatService chatService;

    public MessageRepository(Application application, String token){
        ChatDatabase chatDatabase = ChatDatabase.getDatabase(application);
        messageDao = chatDatabase.messageDao();
        chatService = new RetrofitNetwork(token,1).getChatService();
    }

    public List<Message> getMessagesOfChat(String chat)  {
        List<Message> messages;
        messageDao.deletAll();
        try {
            messages = chatService.getMessagesOfChat(chat).execute().body();
            for (Message message : messages){
                messageDao.insert(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messageDao.getMessageOfChat(chat);
    }

    public Message insert(Message message){
        Message newMessage = null;
        try {
            newMessage = chatService.addMessage(message).execute().body();
            messageDao.insert(newMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
}
