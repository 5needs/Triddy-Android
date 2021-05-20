package edu.eci.ieti.triddy.android.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.eci.ieti.triddy.android.dao.ChatDao;
import edu.eci.ieti.triddy.android.dao.MessageDao;
import edu.eci.ieti.triddy.android.model.Chat;
import edu.eci.ieti.triddy.android.model.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Chat.class, Message.class}, version = 1, exportSchema = false)
public abstract class ChatDatabase extends RoomDatabase {

    public abstract ChatDao chatDao();
    public abstract MessageDao messageDao();
    private static volatile ChatDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ChatDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChatDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ChatDatabase.class, "chat_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
