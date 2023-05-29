package com.example.bayannails.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.bayannails.R;

public class SignUp_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("התחברות");
        EditText et_name, et_familyName, et_email, et_Phone, et_password;
        Button btn_signUp;
        et_name = findViewById(R.id.etName);
        et_familyName = findViewById(R.id.etFamilyName);
        et_email = findViewById(R.id.etEmail);
        et_Phone = findViewById(R.id.etPhone);
        et_password = findViewById(R.id.etPassword);
        btn_signUp = findViewById(R.id.buttonSignUp);
    }
}