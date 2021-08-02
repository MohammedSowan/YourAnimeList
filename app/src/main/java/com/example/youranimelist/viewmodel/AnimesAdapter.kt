package com.example.youranimelist.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youranimelist.databinding.RowAnimeBinding
import com.example.youranimelist.objects.Anime

class AnimesAdapter: ListAdapter<Anime, AnimesAdapter.ViewHolder>(AnimesItemCallback()) {

    //  var animes:List<Anime> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binder: RowAnimeBinding = RowAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anime = getItem(position)

        holder.binder.animeTitle.text = anime.name
        holder.binder.animeEpisodes.text = "Episodes: "+ anime.episodes
        holder.binder.animeRating.text ="Rating: " + anime.rating
        holder.binder.animeDescription.text = "Description: " + anime.description
        holder.binder.animeGenre.text ="Genre: "+ anime.genre

    }


    class ViewHolder(val binder: RowAnimeBinding) : RecyclerView.ViewHolder(binder.root) {}


    class AnimesItemCallback : DiffUtil.ItemCallback<Anime>() {

        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem?.id == newItem?.id
        }

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem == newItem
        }

    }

    }
