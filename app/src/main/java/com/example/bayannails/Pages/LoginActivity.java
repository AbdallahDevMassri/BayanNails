package com.example.bayannails.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bayannails.Classes.User;
import com.example.bayannails.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference userRef;

    // Create a final admin user with fixed name and password

    User adminUser =new User("Abdallah","Massri","a.v.e@live.com","","0523239955","abd123",null);

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

        // Get reference to the "users" node in the Firebase database
        userRef = FirebaseDatabase.getInstance().getReference("users");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etName.getText().toString().trim();
                String password = etPass.getText().toString().trim();

                // Check if the user is admin
                if (username.equals("abd123") && password.equals("0523239955")) {
                    // User is admin, proceed to MainPage
                    // After validating the user in LoginActivity
                    Intent intent = new Intent(LoginActivity.this,MainPage.class);
                    intent.putExtra("userName", username); // Pass the user name as an extra
                    startActivity(intent);
                } else {
                    // Check if the user exists in the Firebase database
                    Query query = userRef.orderByChild("userName").equalTo(username);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // User exists, proceed to MainPage
                                startActivity(new Intent(LoginActivity.this, MainPage.class));
                            } else {
                                // User does not exist, show toast message and navigate to SignUpActivity
                                Toast.makeText(LoginActivity.this, "המשתמש לא קיים נא להעשות הרשמה ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this,SignUp_activity.class));
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle database error
                            Toast.makeText(LoginActivity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUp_activity.class));
            }
        });
    }
}
