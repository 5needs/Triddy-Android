package edu.eci.ieti.triddy.android.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "Message", primaryKeys = {"chatId", "date"})
public class Message {

    private String user;
    private String content;
    @NonNull
    @TypeConverters(DateConverter.class)
    private Date date;
    @NonNull
    private String chatId;

    public Message(String user, String content, Date date, String chatId){
        this.user = user;
        this.content = content;
        this.date = date;
        this.chatId = chatId;
    }

    public Message (){

    }

    public String getUser(){
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getChatId(){
        return this.chatId;
    }

    public void setChatId(String chatId){
        this.chatId = chatId;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Message[ user= '%s', content= '%s', date= '%s', chatId= '%s']"
                ,this.user,this.content,this.date,this.chatId);
    }
}
