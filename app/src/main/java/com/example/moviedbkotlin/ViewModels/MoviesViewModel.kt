package com.example.moviedbkotlin.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedbkotlin.Models.Movies
import com.example.moviedbkotlin.Services.MoviesService

class MoviesViewModel(var page: Int, var with_genres: String): ViewModel() {
    private var mMoviesService = MoviesService(page, with_genres)

    fun getMoviesData(): MutableLiveData<Movies>? {
        return mMoviesService.loadMoviesData()
    }
}