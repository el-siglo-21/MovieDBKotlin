package com.example.moviedbkotlin.DataSources

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviedbkotlin.Interfaces.MoviesService
import com.example.moviedbkotlin.Models.Movie
import com.example.moviedbkotlin.Models.Movies
import com.example.moviedbkotlin.Models.State
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MoviesDataSource (
    private val with_genres: String,
    private val moviesService: MoviesService,
    private val compositeDisposable: CompositeDisposable
)
    : PageKeyedDataSource<Int, Movie>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            moviesService.getMovies("668db373e36ebd9085d4685d02318231", 1, with_genres)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(
                            response.results,
                            null,
                            2
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            moviesService.getMovies("668db373e36ebd9085d4685d02318231", params.key, with_genres)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.results,
                            params.key + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}
