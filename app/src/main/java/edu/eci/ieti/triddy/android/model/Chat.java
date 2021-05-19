package edu.eci.ieti.triddy.android.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Chat")
public class Chat {
    @NonNull
    @PrimaryKey
    private String id;
    private String user1;
    private String user2;
    @Embedded
    private Message lastMessage;

    public Chat(String user1, String user2, Message lastMessage){
        this.user1 = user1;
        this.user2 = user2;
        this.lastMessage = lastMessage;
    }

    public Chat(){

    }

    public String getUser1(){
        return this.user1;
    }

    public void setUser1(String user1){
        this.user1 = user1;
    }

    public String getUser2(){
        return this.user2;
    }

    public void setUser2(String user2){
        this.user2 = user2;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Message getLastMessage(){
        return this.lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public String toString() {
        return String.format("Chat[ id= '%s', user1= '%s', user2= '%s', lastMessage= '%s']"
                ,this.id,this.user1,this.user2, this.lastMessage);
    }
}
