package edu.eci.ieti.triddy.android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import edu.eci.ieti.triddy.android.model.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message message);

    @Query("DELETE FROM Message")
    void deletAll();

    @Query("SELECT * FROM Message")
    List<Message> getAllMessages();

    @Query("SELECT * FROM Message WHERE chatId = :chatId")
    List<Message> getMessageOfChat(String chatId);
}
