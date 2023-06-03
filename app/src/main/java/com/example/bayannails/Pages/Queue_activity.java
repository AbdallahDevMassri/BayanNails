package com.example.bayannails.Pages;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.bayannails.R;
import com.example.bayannails.Classes.Order;
import com.example.bayannails.Classes.OrderAdapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;

public class Queue_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        // Retrieve the order list from the intent extras
        ArrayList<Order> orderList = (ArrayList<Order>) getIntent().getSerializableExtra("orderList");
// Create an instance of the RecyclerView and set its layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerViewQueue);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Create an instance of the OrderAdapter and set it as the adapter for the RecyclerView
        OrderAdapter orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);

    }
}