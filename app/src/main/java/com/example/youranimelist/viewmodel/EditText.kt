package com.example.youranimelist.viewmodel

import android.widget.EditText

val EditText.value: String
    get() = text.toString().trim()