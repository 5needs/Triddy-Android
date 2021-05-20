package edu.eci.ieti.triddy.android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import edu.eci.ieti.triddy.android.model.Calification;

import java.util.List;

@Dao
public interface CalificationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Calification calification);

    @Query("DELETE FROM Calification")
    void deletAll();

    @Query("SELECT * FROM Calification")
    List<Calification> getAllCalfications();
}
