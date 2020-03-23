package com.example.moviedbkotlin.Adapters

//import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviedbkotlin.Models.Movies
import com.example.moviedbkotlin.MoviesActivity
import com.example.moviedbkotlin.R
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.list_movie.view.*

/*
class MoviesAdapter (var context: MoviesActivity, var mMovies: Movies? = Movies()): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MoviesAdapter.MoviesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_movie, p0, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(p0: MoviesAdapter.MoviesViewHolder, p1: Int) {
        p0.tvMovieTitle?.text = mMovies?.results?.get(p1)?.title ?: null
        p0.tvMovieOverview?.text = mMovies?.results?.get(p1)?.overview ?: null
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500" + mMovies?.results?.get(p1)?.poster_path)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(p0.ivMovie)
    }

    override fun getItemCount(): Int {
        return mMovies?.results?.size!!
    }

    class MoviesViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvMovieTitle = view.TVMovieTitle
        val tvMovieOverview = view.TVMovieOverview
        val ivMovie = view.IVMovie
    }
}*/
