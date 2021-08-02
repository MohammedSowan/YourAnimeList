package com.example.youranimelist.firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youranimelist.R;
import com.example.youranimelist.objects.FirebaseAnime;

import java.util.List;

public class FirebaseRecyclerView extends RecyclerView.Adapter {

    List<FirebaseAnime> firebaseAnimeList;

    private FirebaseAnimeListener firebaseAnimeListener;

    public FirebaseRecyclerView(List<FirebaseAnime> firebaseAnimeList, FirebaseAnimeListener firebaseAnimeListener) {
        this.firebaseAnimeList = firebaseAnimeList;
        this.firebaseAnimeListener=firebaseAnimeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_anime,parent,false);
        RecyclerView.ViewHolder viewHolder = new ViewHolderClass(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;

        FirebaseAnime firebaseAnime=firebaseAnimeList.get(position);
        viewHolderClass.title.setText(firebaseAnime.getName());
        viewHolderClass.episodes.setText("Episodes: "+firebaseAnime.epsToString());
        viewHolderClass.description.setText("Description: "+firebaseAnime.getDescription());
        viewHolderClass.rating.setText("Rating: "+firebaseAnime.ratingToString());
    }

    @Override
    public int getItemCount() {
        return firebaseAnimeList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView title,episodes,rating,description,genre;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.animeTitle);
            episodes=itemView.findViewById(R.id.animeEpisodes);
            rating=itemView.findViewById(R.id.animeRating);
            description=itemView.findViewById(R.id.animeDescription);
            genre=itemView.findViewById(R.id.animeGenre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FirebaseAnime firebaseAnime=firebaseAnimeList.get(getAbsoluteAdapterPosition());
                    firebaseAnimeListener.firebaseAnimeClicked(firebaseAnime);
                }
            });

        }
    }

}
