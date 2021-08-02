package com.example.youranimelist.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.youranimelist.viewmodel.AnimesViewModel
import com.example.youranimelist.R

class AnimesFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstant: Bundle?){
        super.onCreate(savedInstant)
        setContentView(R.layout.activity_animes_fragment)
    }

}