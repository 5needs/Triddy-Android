package edu.eci.ieti.triddy.android.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.eci.ieti.triddy.android.dao.CalificationDao;
import edu.eci.ieti.triddy.android.model.Calification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Calification.class}, version = 1, exportSchema = false)
public abstract class CalificationDatabase extends RoomDatabase {

    public abstract CalificationDao calificationDao();
    private static volatile CalificationDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CalificationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CalificationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CalificationDatabase.class, "chat_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
