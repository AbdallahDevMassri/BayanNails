package com.example.bayannails.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bayannails.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOk, btnSignUp;
        EditText etName, etPass;
        btnOk = findViewById(R.id.buttonOk);
        btnSignUp = findViewById(R.id.buttonSignUp);
        etName = findViewById(R.id.etUserName);
        etPass = findViewById(R.id.etPassword);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainPage.class));
            }
        });
    }
}