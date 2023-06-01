package com.example.bayannails.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;


import com.example.bayannails.MapsActivity;
import com.example.bayannails.R;

public class MainPage extends AppCompatActivity {
    String myNumber = "0523239955";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ImageView iv_gallery, iv_queue, iv_maps, iv_change, iv_cancel, iv_add, iv_instagram,
                iv_watssup, iv_call, iv_facebook;


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
                // Example: start a new activity
                Intent intent = new Intent(MainPage.this, Queue_activity.class);
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

    }
}

