package edu.eci.ieti.triddy.android.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.eci.ieti.triddy.android.dao.RentDao;
import edu.eci.ieti.triddy.android.model.Rent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Rent.class}, version = 1, exportSchema = false)
public abstract class RentDatabase extends RoomDatabase {

    public abstract RentDao rentDao();
    private static volatile RentDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RentDatabase.class, "rent_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}