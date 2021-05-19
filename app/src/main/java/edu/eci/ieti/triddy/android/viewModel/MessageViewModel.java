package com.ieti.triddy.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.eci.ieti.triddy.android.model.Message;
import edu.eci.ieti.triddy.android.repository.MessageRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageViewModel extends AndroidViewModel {
    private MutableLiveData<List<Message>> messages;
    private MutableLiveData<Message> message;
    private MessageRepository messageRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public MessageViewModel(Application application){
        super(application);
    }

    public LiveData<List<Message>> getMessages(String token, String chatId){
        if (messages == null){
            messages = new MutableLiveData<List<Message>>();
            messageRepository = new MessageRepository(getApplication() ,token);
            loadChats(chatId);
        }
        return messages;
    }

    public LiveData<Message> addMessage(String token, Message newMessage){
        messageRepository = new MessageRepository(getApplication() ,token);
        message = new MutableLiveData<Message>();
        executorService.execute(() -> {
            message.postValue(messageRepository.insert(newMessage));
        });
        return message;
    }

    public void loadChats(String chatId){
        executorService.execute(() -> {
            messages.postValue(messageRepository.getMessagesOfChat(chatId));
        });
    }

}
