package com.example.youranimelist.fragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.youranimelist.viewmodel.AddAnimeViewModel
import com.example.youranimelist.viewmodel.AddAnimesViewModelFactory
import com.example.youranimelist.R
import com.example.youranimelist.databinding.FragmentAddAnimeBinding
import com.example.youranimelist.objects.Anime
import com.example.youranimelist.viewmodel.value

class AddAnimeFragment : Fragment(R.layout.fragment_add_anime) {

    private val addAnimeViewModel by viewModels<AddAnimeViewModel>{
        AddAnimesViewModelFactory(requireContext())
    }

    private var _binder: FragmentAddAnimeBinding?=null
     private val binder
     get() = _binder!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binder = FragmentAddAnimeBinding.bind(view)
        binder.btnAddAnime.setOnClickListener {
            val title = binder.AnimeTitleContainer.value
            val episodes = binder.animeEpisodesContainer.value
            val rating = binder.animeRatingContainer.value
            val description = binder.animeDescriptionContainer.value
            val genre = binder.animeGenreContainer.value
            addAnimeViewModel.addAnime(Anime(name = title, episodes = episodes, rating = rating, description = description, genre = genre))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binder=null
    }

}