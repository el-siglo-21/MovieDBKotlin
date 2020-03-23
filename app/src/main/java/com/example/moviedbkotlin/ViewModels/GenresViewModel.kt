package com.example.moviedbkotlin.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedbkotlin.Models.Genre
import com.example.moviedbkotlin.Services.GenresService

class GenresViewModel: ViewModel() {
    private val mGenresService = GenresService()

    fun getGenresData(): MutableLiveData<ArrayList<Genre>>? {
        return mGenresService.loadGenresData()
    }
}