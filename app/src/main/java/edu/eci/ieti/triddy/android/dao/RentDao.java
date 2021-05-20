package edu.eci.ieti.triddy.android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import edu.eci.ieti.triddy.android.model.Rent;
import java.util.List;

@Dao
public interface RentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Rent rent);

    @Query("DELETE FROM Rent")
    void deletAll();

    @Query("SELECT * FROM Rent")
    List<Rent> getAllRents();

    @Query("SELECT * FROM Rent WHERE userLessee = :userEmail")
    List<Rent> getRentsOFUser(String userEmail);

}
