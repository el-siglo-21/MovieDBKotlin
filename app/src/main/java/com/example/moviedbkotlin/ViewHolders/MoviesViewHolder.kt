package com.example.moviedbkotlin.ViewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbkotlin.Models.Movie
import com.example.moviedbkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_movie.view.*

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie?) {
        if (movie != null) {
            itemView.TVMovieTitle.text = movie.title
            itemView.TVMovieOverview.text = movie.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(itemView.IVMovie)
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_movie, parent, false)
            return MoviesViewHolder(view)
        }
    }
}