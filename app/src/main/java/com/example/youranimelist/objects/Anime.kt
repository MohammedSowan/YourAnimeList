package com.example.youranimelist.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anime (
        @PrimaryKey(autoGenerate = true) val id: Int?=null,
        val name:String,
        val episodes: String,
        val rating: String,
        val description: String,
        val genre: String
)
