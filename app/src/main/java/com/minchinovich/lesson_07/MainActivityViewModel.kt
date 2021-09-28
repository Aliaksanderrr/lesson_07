package com.minchinovich.lesson_07

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val aboutAppURL = "http://developer.android.com"

    fun getAboutAppURL(): String{
        return aboutAppURL
    }
}