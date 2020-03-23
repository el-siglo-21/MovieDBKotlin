package com.example.moviedbkotlin.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesViewModelFactory(private val page: Int, private val with_genres: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return MovieListViewModel(with_genres) as T
    }
}