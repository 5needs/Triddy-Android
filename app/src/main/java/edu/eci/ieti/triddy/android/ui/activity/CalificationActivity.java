package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import edu.eci.ieti.triddy.android.model.Calification;
import com.ieti.triddy.viewModel.CalificationViewModel;
import edu.eci.ieti.triddy.android.R;
import edu.eci.ieti.triddy.android.storage.Storage;

public class CalificationActivity extends AppCompatActivity {
    private CalificationViewModel calificationViewModel;
    private Storage storage;
    private String product;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calification);
        calificationViewModel = new ViewModelProvider(this).get(CalificationViewModel.class);
        storage = new Storage( this );
        Intent intent = getIntent();
        this.user = intent.getStringExtra(MyRentsActivity.USERPRODUCT);
        this.product = intent.getStringExtra(MyRentsActivity.PRODUCTID);
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
            Calification calification = new Calification(storage.getEmail(), this.product,(Double) status_d,charac_d,
                                com_pr.getText().toString(), this.user, status_u_d, com_us.getText().toString());
            calificationViewModel.addCalification(storage.getToken(), calification).observe(this, calification1 -> {
                final Calification newCalification = calification1;
            });
        }
    }
}