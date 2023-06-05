package com.example.bayannails.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;

import com.example.bayannails.Classes.ImageAdapter;
import com.example.bayannails.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import java.util.List;
import java.util.ArrayList;

public class Gallery_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageAdapter = new ImageAdapter();
        recyclerView.setAdapter(imageAdapter);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("userImage");

        imageRef.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {
                if (task.isSuccessful()) {
                    List<StorageReference> items = task.getResult().getItems();
                    List<String> imageUrls = new ArrayList<>();
                    for (StorageReference item : items) {
                        imageUrls.add(item.getDownloadUrl().toString());
                    }
                    imageAdapter.setData(imageUrls);
                    imageAdapter.notifyDataSetChanged();
                } else {
                    // Handle the error
                }
            }
        });
    }
}
