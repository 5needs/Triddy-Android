package edu.eci.ieti.triddy.android.repository;

import android.app.Application;

import edu.eci.ieti.triddy.android.model.Calification;
import edu.eci.ieti.triddy.android.dao.CalificationDao;
import edu.eci.ieti.triddy.android.database.CalificationDatabase;
import edu.eci.ieti.triddy.android.network.RetrofitNetwork;
import edu.eci.ieti.triddy.android.network.service.CalificationService;

public class CalificationRepository {
    private CalificationDao calificationDao;
    private CalificationService calificationService;

    public CalificationRepository(Application application, String token){
        CalificationDatabase calificationDatabase = CalificationDatabase.getDatabase(application);
        calificationDao = calificationDatabase.calificationDao();
        calificationService = new RetrofitNetwork(token,1).getCalificationService();
    }

    public Calification insert(Calification calification){
        Calification newCalification = null;
        try {
            newCalification = calificationService.addCalification(calification).execute().body();
            calificationDao.insert(newCalification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCalification;
    }

}
