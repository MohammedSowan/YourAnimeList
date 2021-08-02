package com.example.youranimelist.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.youranimelist.R;
import com.example.youranimelist.activity.AnimesFragmentActivity;
import com.example.youranimelist.activity.ProfileActivity;
import com.example.youranimelist.objects.Anime;
import com.example.youranimelist.objects.FirebaseAnime;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseList extends AppCompatActivity implements FirebaseAnimeListener {
    List<FirebaseAnime> firebaseAnimes;
    DatabaseReference databaseReference;
    public static final String ANIME_KEY = "Anime";


    RecyclerView recyclerView;
    FirebaseRecyclerView firebaseRecyclerView;
    Button List2Btn, ProfileBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_list);
        //      List<FirebaseAnime> FirebaseAnimes = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseAnimes = new ArrayList<>();


        List2Btn = findViewById(R.id.List2Btn);
        List2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirebaseList.this, AnimesFragmentActivity.class);
                startActivity(intent);
            }
        });


        ProfileBtn = findViewById(R.id.ProfileBtn);
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirebaseList.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("AnimeList");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot ds : datasnapshot.getChildren()) {
                    FirebaseAnime data = ds.getValue(FirebaseAnime.class);
                    firebaseAnimes.add(data);
                }
                firebaseRecyclerView = new FirebaseRecyclerView(firebaseAnimes, FirebaseList.this);
                recyclerView.setAdapter(firebaseRecyclerView);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        }
        @Override
        public void firebaseAnimeClicked (FirebaseAnime firebaseAnime){
            Intent intent = new Intent(this, FirebaseAnimeActivity.class);
            intent.putExtra(ANIME_KEY, firebaseAnime);
            startActivity(intent);
    }
}
