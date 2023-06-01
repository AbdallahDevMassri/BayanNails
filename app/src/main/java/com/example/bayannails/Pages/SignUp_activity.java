package com.example.bayannails.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bayannails.Classes.User;
import com.example.bayannails.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;

public class SignUp_activity extends AppCompatActivity {

    private EditText etName, etFamilyName, etEmail, etPhone, etPassword;
    private Button btnSignUp;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("התחברות");

        etName = findViewById(R.id.etName);
        etFamilyName = findViewById(R.id.etFamilyName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.buttonSignUp);
        userRef = FirebaseDatabase.getInstance().getReference("users");
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate the inputs
                if (validateInputs()) {
                    // Navigate to MainPage activity
                    startActivity(new Intent(SignUp_activity.this, MainPage.class));
                    finish(); // Finish the current activity if navigation is successful
                }
            }
        });
    }

    private boolean validateInputs() {
        String name = etName.getText().toString().trim();
        String familyName = etFamilyName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Check if any input is empty
        if (TextUtils.isEmpty(name)) {
            etName.setError("Please enter your name");
            etName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(familyName)) {
            etFamilyName.setError("Please enter your family name");
            etFamilyName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Please enter your email");
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            etPhone.setError("Please enter your phone number");
            etPhone.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return false;
        }

        // Check if email is valid
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return false;
        }

        // Check if phone number is valid (e.g., contains only digits and has a certain length)
        if (!TextUtils.isDigitsOnly(phone) || phone.length() != 10) {
            etPhone.setError("Please enter a valid phone number");
            etPhone.requestFocus();
            return false;
        }

        // Check if email already exists in Firebase

        Query emailQuery = userRef.orderByChild("email").equalTo(email);
        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    etEmail.setError("Email already exists");
                    etEmail.requestFocus();
                } else {
                    // Email does not exist, continue with other validations
                    // ...

                    // Check if phone number already exists in Firebase
                    Query phoneQuery = userRef.orderByChild("phoneNumber").equalTo(phone);
                    phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                etPhone.setError("Phone number already exists");
                                etPhone.requestFocus();
                            } else {


                                User user =new User(name,familyName,email,password,phone,null);
                                userRef.push().setValue(user);
                                Toast.makeText(SignUp_activity.this, "welcome "+name + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent().putExtra("name",name);
                                startActivity(new Intent(SignUp_activity.this, MainPage.class));
                                finish(); // Finish the current activity if navigation is successful
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle database error
                            Toast.makeText(SignUp_activity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(SignUp_activity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Return false here, as the final result will be determined asynchronously
        return false;
    }
}
