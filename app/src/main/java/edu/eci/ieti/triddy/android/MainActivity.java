package edu.eci.ieti.triddy.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_product);

        //product name
        String name = "Bata de laboratorio";
        TextView textView = findViewById(R.id.textView5);
        textView.setText(name);

    }
}