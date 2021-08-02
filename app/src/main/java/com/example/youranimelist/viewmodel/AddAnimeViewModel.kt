package com.example.youranimelist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.youranimelist.objects.Anime

class AddAnimeViewModel(animesDao: AnimesDao):ViewModel() {

    private val addAnimeRepo = AddAnimeRepo(animesDao)

    fun addAnime(anime: Anime) {
        addAnimeRepo.insertAnime(anime)
    }
}