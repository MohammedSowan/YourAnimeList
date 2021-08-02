package com.example.youranimelist.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.youranimelist.R;
import com.example.youranimelist.objects.FirebaseAnime;

import static com.example.youranimelist.firebase.FirebaseList.ANIME_KEY;

public class FirebaseAnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_anime);

        TextView tvFirebaseAnime=findViewById(R.id.tvFirebaseAnime);
        FirebaseAnime firebaseAnime= (FirebaseAnime) getIntent().getSerializableExtra(ANIME_KEY);
        tvFirebaseAnime.setText(firebaseAnime.getName());

        TextView tvFirebaseAnimeDescription=findViewById(R.id.tvFirebaseAnimeDescription);
        tvFirebaseAnimeDescription.setText(firebaseAnime.getDescription());
    }
}