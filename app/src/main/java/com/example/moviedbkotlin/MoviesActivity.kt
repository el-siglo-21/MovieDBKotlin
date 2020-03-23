package com.example.moviedbkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbkotlin.Adapters.MovieListAdapter
//import com.example.moviedbkotlin.Adapters.MoviesAdapter
import com.example.moviedbkotlin.Models.Movies
import com.example.moviedbkotlin.Models.State
import com.example.moviedbkotlin.ViewModels.MovieListViewModel
import com.example.moviedbkotlin.ViewModels.MoviesViewModel
import com.example.moviedbkotlin.ViewModels.MoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var loading: Boolean = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var page: Int = 1
    var with_genres: String? = null

    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        with_genres = intent.getIntExtra("id", 0).toString()

        /*linearLayoutManager = LinearLayoutManager(this@MoviesActivity)
        RVMovies.layoutManager = linearLayoutManager;
        RVMovies.hasFixedSize()

        getMoviesData(page, with_genres!!)*/

        viewModel = ViewModelProviders.of(this@MoviesActivity, MoviesViewModelFactory(page, with_genres!!))
            .get(MovieListViewModel::class.java)
        initAdapter()
        initState()
    }

    private fun initAdapter() {
        movieListAdapter = MovieListAdapter { viewModel.retry() }
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = movieListAdapter
        viewModel.movieList.observe(this, Observer {
            movieListAdapter.submitList(it)
        })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                movieListAdapter.setState(state ?: State.DONE)
            }
        })
    }

    /*private fun getMoviesData(page: Int, with_genres: String) {
        val mMoviesViewModel = ViewModelProviders.of(this@MoviesActivity, MoviesViewModelFactory(page, with_genres!!)).get(MoviesViewModel::class.java)
        mMoviesViewModel.getMoviesData()?.observe(this, Observer<Movies> { androidList ->
            RVMovies.adapter = MoviesAdapter(this@MoviesActivity, androidList as Movies)
            RVMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        visibleItemCount = linearLayoutManager.childCount
                        totalItemCount = linearLayoutManager.itemCount
                        pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()

                        if (loading) {
                            PBMovies.visibility = View.VISIBLE
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                loading = false

                                //Do pagination
                                if (androidList.page < androidList.total_pages) {
                                    var newPage: Int = 0
                                    newPage = androidList.page + 1
                                    getMoviesData(newPage, with_genres)
                                    loading = true
                                }
                                PBMovies.visibility = View.GONE
                            }
                            PBMovies.visibility = View.GONE
                        }
                    }
                }
            })
        })
    }*/
}
