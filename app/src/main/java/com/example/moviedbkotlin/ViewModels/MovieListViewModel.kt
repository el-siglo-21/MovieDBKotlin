package com.example.moviedbkotlin.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviedbkotlin.DataSources.MoviesDataSource
import com.example.moviedbkotlin.DataSources.MoviesDataSourceFactory
import com.example.moviedbkotlin.Interfaces.MoviesService
import com.example.moviedbkotlin.Models.Movie
import com.example.moviedbkotlin.Models.Movies
import com.example.moviedbkotlin.Models.State
import io.reactivex.disposables.CompositeDisposable

class MovieListViewModel(var with_genres: String) : ViewModel() {

    private val moviesService = MoviesService.getService()
    var movieList: LiveData<PagedList<Movie>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 20
    private val moviesDataSourceFactory: MoviesDataSourceFactory

    init {
        moviesDataSourceFactory = MoviesDataSourceFactory(with_genres, compositeDisposable, moviesService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        movieList = LivePagedListBuilder<Int, Movie>(moviesDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<MoviesDataSource,
            State>(moviesDataSourceFactory.moviesDataSourceLiveData, MoviesDataSource::state)

    fun retry() {
        moviesDataSourceFactory.moviesDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return movieList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}