package com.ieti.triddy.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.eci.ieti.triddy.android.model.Calification;
import edu.eci.ieti.triddy.android.repository.CalificationRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalificationViewModel extends AndroidViewModel {

    private MutableLiveData<Calification> calification;
    private CalificationRepository calificationRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public CalificationViewModel(Application application){ super(application);}

    public LiveData<Calification> addCalification(String token, Calification newCalification){
        calificationRepository = new CalificationRepository(getApplication(),token);
        calification = new MutableLiveData<Calification>();
        executorService.execute(() -> {
            calification.postValue(calificationRepository.insert(newCalification));
        });
        return calification;
    }

}
