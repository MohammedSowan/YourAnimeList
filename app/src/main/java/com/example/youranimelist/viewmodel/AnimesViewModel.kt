package com.example.youranimelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youranimelist.objects.Anime

class AnimesViewModel(animesDao: AnimesDao) : ViewModel(){


    private val animesRepo = AnimesRepo(animesDao)

    val animes:LiveData<List<Anime>> = animesRepo.animes

    fun deleteAnime(anime: Anime) {
        animesRepo.deleteAnime(anime)
    }



}