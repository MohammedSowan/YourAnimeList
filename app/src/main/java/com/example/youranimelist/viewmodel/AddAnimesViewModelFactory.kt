package com.example.youranimelist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youranimelist.viewmodel.AppDatabase.Companion.getInstance

class AddAnimesViewModelFactory(private val context: Context):
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddAnimeViewModel(getInstance(context).animesDao()) as T
    }



}