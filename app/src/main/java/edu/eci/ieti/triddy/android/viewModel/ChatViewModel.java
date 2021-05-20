package com.ieti.triddy.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.eci.ieti.triddy.android.model.Chat;
import edu.eci.ieti.triddy.android.repository.ChatRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatViewModel extends AndroidViewModel {
    private MutableLiveData<List<Chat>> chats;
    private ChatRepository chatRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public ChatViewModel(Application application){
        super(application);
    }

    public LiveData<List<Chat>> getChats(String token, String user){
        if (chats == null){
            chats = new MutableLiveData<List<Chat>>();
            chatRepository = new ChatRepository(getApplication() ,token);
            loadChats(user);
        }
        return chats;
    }

    public void loadChats(String user){
        executorService.execute(() -> {
            chats.postValue(chatRepository.getChatsFromUser(user));
        });

    }
}
