package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import edu.eci.ieti.triddy.android.model.Message;
import edu.eci.ieti.triddy.android.storage.Storage;
import edu.eci.ieti.triddy.android.adapter.MessageAdapter;

import com.ieti.triddy.viewModel.MessageViewModel;
import edu.eci.ieti.triddy.android.R;

import java.util.Date;

public class ChatActivity extends AppCompatActivity {
    private Storage storage;
    MessageViewModel messageViewModel;
    private MessageAdapter messageAdapter;
    private String chatId;
    private String user;
    ListView messageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        storage = new Storage( this );
        Intent intent = getIntent();
        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        messageAdapter = new MessageAdapter(this, "user1@mail.com");
        chatId = intent.getStringExtra(ChatListActivity.CHATID);
        user = intent.getStringExtra(ChatListActivity.USERCHAT);
        setTitle(user);
        messageView = (ListView) findViewById(R.id.messages_view);
        configureListView();
    }

    private void configureListView(){
        messageView.setAdapter( messageAdapter );
        messageViewModel.getMessages(storage.getToken(), this.chatId).observe(this, messages -> {
            runOnUiThread(() -> {
                messageAdapter.addMessages(messages);
            });
        });
    }

    public void sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.editText_chat);
        if(editText.getText().toString().length() > 0){
            Message message = new Message(storage.getEmail(),editText.getText().toString(),new Date(),this.chatId);
            messageViewModel.addMessage(storage.getToken(), message).observe(this, message2 -> {
                runOnUiThread(() -> {
                    messageAdapter.addMessage(message2);
                });
            });
            editText.getText().clear();
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}