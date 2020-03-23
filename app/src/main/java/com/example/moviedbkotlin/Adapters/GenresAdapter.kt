package com.example.moviedbkotlin.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbkotlin.Interfaces.ItemClickListener
import com.example.moviedbkotlin.MainActivity
import com.example.moviedbkotlin.Models.Genre
import com.example.moviedbkotlin.R
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.list_genre.view.*

class GenresAdapter(var context: MainActivity, var mGenreList: ArrayList<Genre>? = ArrayList<Genre>(), private val itemClick: ItemClickListener): RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {
    companion object {
        var mItemClickListener: ItemClickListener? = null
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GenresAdapter.GenresViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_genre, p0, false)
        return GenresViewHolder(view)
    }

    override fun onBindViewHolder(p0: GenresAdapter.GenresViewHolder, p1: Int) {
        mItemClickListener = itemClick
        p0.tvGenreName?.text = mGenreList?.get(p1)?.name

        RxView.clicks(p0.mView).subscribe {
            mItemClickListener!!.onItemClick(p1)
        }
    }

    override fun getItemCount(): Int {
        return mGenreList!!.size
    }

    class GenresViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvGenreName = view.tvGenreName
        val mView = view
    }
}