package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import edu.eci.ieti.triddy.android.model.Calification;
import com.ieti.triddy.viewModel.CalificationViewModel;
import edu.eci.ieti.triddy.android.R;

public class CalificationActivity extends AppCompatActivity {
    private CalificationViewModel calificationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calification);
        calificationViewModel = new ViewModelProvider(this).get(CalificationViewModel.class);
    }

    public void send(View view){
        Float status =  ((RatingBar) findViewById(R.id.ratingBar_sta)).getRating();
        Float charac = ((RatingBar) findViewById(R.id.ratingBar_char)).getRating();
        Float status_u = ((RatingBar) findViewById(R.id.ratingBar_sta_u)).getRating();
        if ((status == 0 ) || (charac == 0) || (status_u == 0)){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Todas las calificaciones deben ser mayores que cero")
                    .setCancelable(true).show();
        }
        else{
            EditText com_pr = (EditText) findViewById(R.id.editTextTextMultiLine_pro);
            EditText com_us = (EditText) findViewById(R.id.editTextTextMultiLine_us);
            Double status_d = Double.valueOf(status.toString());
            Double charac_d = Double.valueOf(charac.toString());
            Double status_u_d = Double.valueOf(status_u.toString());
            String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMiIsInJvbGVzIjoidXNlciIsImlhdCI6MTYyMDY4OTA4OH0.NmoNbhffcc2Nr2ZMqBC6qsDZc9D59JWO3Rym3y1s5zI";
            Calification calification = new Calification("user1@mail.com", "product",(Double) status_d,charac_d,
                                com_pr.getText().toString(), "user", status_u_d, com_us.getText().toString());
            calificationViewModel.addCalification(token, calification).observe(this, calification1 -> {
                final Calification newCalification = calification1;
            });
        }
    }
}