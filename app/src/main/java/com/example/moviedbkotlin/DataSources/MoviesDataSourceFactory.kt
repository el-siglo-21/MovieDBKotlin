package com.example.moviedbkotlin.DataSources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviedbkotlin.Interfaces.MoviesService
import com.example.moviedbkotlin.Models.Movie
import com.example.moviedbkotlin.Models.Movies
import io.reactivex.disposables.CompositeDisposable

class MoviesDataSourceFactory(
    private val with_genres: String,
    private val compositeDisposable: CompositeDisposable,
    private val moviesService: MoviesService
)
    : DataSource.Factory<Int, Movie>() {

    val moviesDataSourceLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = MoviesDataSource(with_genres, moviesService, compositeDisposable)
        moviesDataSourceLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }
}