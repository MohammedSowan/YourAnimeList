package com.example.youranimelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.youranimelist.objects.Anime

@Dao
interface AnimesDao {

    @Query("SELECT * FROM Anime")
    fun getAnimes(): LiveData<List<Anime>>

    @Insert
    fun insertAnime(anime: Anime)
    @Delete
    fun deleteAnime(anime: Anime)

    @Query("DELETE FROM Anime")
    fun deleteAll()


}