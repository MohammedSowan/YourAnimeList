package com.example.youranimelist.viewmodel

import com.example.youranimelist.objects.Anime

class AddAnimeRepo(private val animesDao: AnimesDao) {

    fun insertAnime(anime: Anime) {
        Thread {
            animesDao.insertAnime(anime)
        }.start()
    }
}