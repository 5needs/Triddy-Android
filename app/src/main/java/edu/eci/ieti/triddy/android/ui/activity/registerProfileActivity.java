package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.eci.ieti.triddy.android.R;

public class registerProfileActivity extends AppCompatActivity implements View.OnClickListener{

    EditText firstName;
    EditText lastName;
    EditText address;
    EditText email;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(firstName)) {
            firstName.setError("First name is required!");
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }

        if (isEmpty(email)) {
            email.setError("Enter valid email!");
        }

        if (isEmpty(address)) {
            address.setError("Address is required!");
        }

        if(!isEmpty(firstName) && !isEmpty(lastName) && !isEmpty(email) && !isEmpty(address)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        checkDataEntered();
    }
}