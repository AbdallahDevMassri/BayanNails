package com.example.bayannails.Pages;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.example.bayannails.Classes.User;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.Calendar;
import com.example.bayannails.Classes.Order;

import com.example.bayannails.MapsActivity;
import com.example.bayannails.R;

import java.util.ArrayList;
import java.util.List;
////////

public class MainPage extends AppCompatActivity {
    String myNumber = "0523239955";
    List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ImageView iv_gallery, iv_queue, iv_maps, iv_change, iv_cancel, iv_add, iv_instagram,
                iv_watssup, iv_call, iv_facebook;

        // Retrieve the user name from the Intent extras
        String userName = getIntent().getStringExtra("userName");

        // Set the user name as the title
        setTitle("Welcome"+userName);

        iv_gallery = findViewById(R.id.ivGallery);
        iv_queue = findViewById(R.id.ivQueue);
        iv_maps = findViewById(R.id.ivMaps);
        iv_change = findViewById(R.id.ivChange);
        iv_cancel = findViewById(R.id.ivCancel);
        iv_add = findViewById(R.id.ivAdd);
        iv_instagram = findViewById(R.id.ivInstagram);
        iv_facebook = findViewById(R.id.ivFacebook);
        iv_watssup = findViewById(R.id.ivWatssup);
        iv_call = findViewById(R.id.ivCall);

        iv_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Gallery_Activity.class);
                startActivity(intent);
            }
        });

        iv_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform navigation to another page here

                Intent intent = new Intent(MainPage.this, Queue_activity.class);
                intent.putExtra("orderList", (ArrayList<Order>) orderList);
                startActivity(intent);
            }
        });

        iv_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform navigation to another page here
                // Example: start a new activity
                Intent intent = new Intent(MainPage.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        iv_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform navigation to Instagram here
                Uri uri = Uri.parse("https://instagram.com/bayan_nails.5.2?igshid=MzRlODBiNWFlZA==");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        iv_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform navigation to Facebook here
                Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=100067890501451&mibextid=ZbWKwL");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        iv_watssup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform navigation to WhatsApp here
                String message = "Hello, this is a message from my app BayanNails!";
                Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + myNumber + "&text=" + Uri.encode(message));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform phone call here
                Uri uri = Uri.parse("tel:" + myNumber);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderList();
            }
        });

        iv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform change order functionality here
                // You can show a dialog or start a new activity to update the order
                Toast.makeText(MainPage.this, "Change order clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showDateTimePicker() {
        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Show date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainPage.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        final int selectedYearFinal = selectedYear;
                        final int selectedMonthFinal = selectedMonth;
                        final int selectedDayFinal = selectedDayOfMonth;

                        // Show time picker dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainPage.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int selectedHourOfDay, int selectedMinute) {
                                        // Create an Order object with the selected hour
                                        Order selectedOrder = new Order(selectedDayFinal, selectedMonthFinal, selectedYearFinal, selectedHourOfDay);

                                        // Update the orderList
                                        orderList.add(selectedOrder);

                                        // Update the database with the selected order
                                        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("orders");
                                        String orderId = ordersRef.push().getKey();
                                        ordersRef.child(orderId).setValue(selectedOrder);

                                        // Retrieve the user name from the Intent extras
                                        String userName = getIntent().getStringExtra("userName");

                                        // Update the database with the selected order and user name
                                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users");
                                        if (userName != null) {
                                            userRef = userRef.child(userName);
                                            userRef.child("order").setValue(selectedOrder);

                                            Toast.makeText(MainPage.this, "Order added to "+ userName, Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Handle the case where userName is null (e.g., show an error message)
                                        }
                                    }
                                }, hour, 0, true); // Set is24HourView to true and remove the minute parameter
                        timePickerDialog.show();
                    }
                }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()); // Set minimum date to the current date
        datePickerDialog.show();
    }



    private void showOrderList() {
        //  start a new activity to display the order list
        // Perform logic to delete the selected order from the orderList and the database
        Toast.makeText(MainPage.this, "Cancel order clicked", Toast.LENGTH_SHORT).show();
    }
}

