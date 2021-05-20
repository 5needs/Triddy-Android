package edu.eci.ieti.triddy.android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.eci.ieti.triddy.android.model.Rent;
import edu.eci.ieti.triddy.android.repository.RentRepository;

public class RentViewModel extends AndroidViewModel {
    private MutableLiveData<List<Rent>> rents;
    private RentRepository rentRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public RentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Rent>> getRents(String token, String user){
        if (rents == null){
            rents = new MutableLiveData<List<Rent>>();
            rentRepository = new RentRepository(getApplication(), token);
            loadRents(user);
        }
        return rents;
    }

    private void loadRents(String user){
        executorService.execute(() ->{
            rents.postValue(rentRepository.getRentsFromUser(user));
        });
    }


}
