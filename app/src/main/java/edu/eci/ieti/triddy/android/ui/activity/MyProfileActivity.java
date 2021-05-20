package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.eci.ieti.triddy.android.R;
import edu.eci.ieti.triddy.android.model.User;
import edu.eci.ieti.triddy.android.network.RetrofitNetwork;
import edu.eci.ieti.triddy.android.storage.Storage;
import retrofit2.Call;
import retrofit2.Response;

public class MyProfileActivity extends AppCompatActivity {

    private Storage storage;
    private RetrofitNetwork retrofitNetwork;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        storage = new Storage( this );

        retrofitNetwork = new RetrofitNetwork(storage.getToken());

        getUserData(storage.getEmail());

        if(storage.getEmail().equals("user1@mail.com")) {
            ImageView imageview = (ImageView) findViewById(R.id.profilephoto);
            imageview.setImageResource(R.drawable.profile1);
        }

    }

    public void getUserData(String email ) {
        executorService.execute( new Runnable() {
            @Override
            public void run() {
                try {
                    Call<User> call = retrofitNetwork.getUserService().getUser(email);
                    Response<User> response = call.execute();
                    System.out.println("-------------------------------------------");

                    if ( response.isSuccessful() ) {
                        setData(response.body());
                    }

                }
                catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        } );
    }

    private void setData(User user) {
        TextView name = findViewById(R.id.textViewName);
        name.setText(user.getFullname());

        TextView email = findViewById(R.id.textViewEmail);
        email.setText(user.getEmail());

        TextView university = findViewById(R.id.textViewU);
        university.setText(user.getUniversity());

        TextView career = findViewById(R.id.textViewCareer);
        career.setText(user.getCareer());
    }

}