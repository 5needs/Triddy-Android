package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.eci.ieti.triddy.android.R;

public class myRewards extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button remedir;
    private ListView mlistView;
    private EditText mEditText;
    private final List<String> mLista = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rewards);

        remedir = findViewById(R.id.btnAgregar);
        mlistView = findViewById(R.id.listView);
        mEditText= findViewById(R.id.etLista);
        remedir.setOnClickListener(this);
        mlistView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar: String text = mEditText.getText().toString().trim();
                mLista.add(text);;
                mEditText.getText().clear();
                mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mLista);
                mlistView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Item Clicked "+position, Toast.LENGTH_SHORT).show();
    }
}