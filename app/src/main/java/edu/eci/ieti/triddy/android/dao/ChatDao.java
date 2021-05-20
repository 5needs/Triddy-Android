package edu.eci.ieti.triddy.android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import edu.eci.ieti.triddy.android.model.Chat;

import java.util.List;

@Dao
public interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Chat chat);

    @Query("DELETE FROM Chat")
    void deletAll();

    @Query("SELECT * FROM Chat")
    List<Chat> getAllChats();

    @Query("SELECT * FROM Chat WHERE user1 = :user OR user2 = :user")
    List<Chat> getChatsFromUser(String user);
}
