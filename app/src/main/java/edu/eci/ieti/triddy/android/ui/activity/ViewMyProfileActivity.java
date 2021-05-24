package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import edu.eci.ieti.triddy.android.R;

public class ViewMyProfileActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_profile);
        button = (Button) findViewById(R.id.button3);
    }

    public void Send (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
