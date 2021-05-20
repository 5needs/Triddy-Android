package edu.eci.ieti.triddy.android.repository;

import android.app.Application;

import edu.eci.ieti.triddy.android.dao.RentDao;
import edu.eci.ieti.triddy.android.database.RentDatabase;
import edu.eci.ieti.triddy.android.model.Rent;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentRepository {
    private RentDao rentDao;

    public RentRepository(Application application, String token){
        RentDatabase rentDatabase = RentDatabase.getDatabase(application);
        rentDao = rentDatabase.rentDao();
    }

    public List<Rent> getRentsFromUser(String user)  {
        List<Rent> rents = null;
        try {
            insertData();
            rents = rentDao.getRentsOFUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rents;
    }

    public Rent insert(Rent rent){
        try {
            rentDao.insert(rent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rent;
    }

    private void insertData(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2021,5,10);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2021,5,15);
        Rent rent = new Rent("1", "Bata", "user2@mail.com", "user1@mail.com",
                            calendar1.getTime(), calendar2.getTime(), "finish");
        insert(rent);
        calendar1.set(2021,5,12);
        calendar2.set(2021,6,25);
        rent = new Rent("2", "Libro", "user3@mail.com", "user1@mail.com",
                calendar1.getTime(), calendar2.getTime(), "active");
        insert(rent);
        calendar1.set(2021,5,12);
        calendar2.set(2021,6,23);
        rent = new Rent("3", "Computador", "user4@mail.com", "user1@mail.com",
                calendar1.getTime(), calendar2.getTime(), "active");
        insert(rent);
    }
}
