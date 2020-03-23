package com.example.moviedbkotlin

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbkotlin.Adapters.GenresAdapter
import com.example.moviedbkotlin.Interfaces.ItemClickListener
import com.example.moviedbkotlin.Models.Genre
import com.example.moviedbkotlin.ViewModels.GenresViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = linearLayoutManager;
        recyclerView.hasFixedSize()

        getGenresData()
    }

    private fun getGenresData() {
        val mGenresViewModel = ViewModelProviders.of(this@MainActivity).get(GenresViewModel::class.java)
        mGenresViewModel.getGenresData()?.observe(this, Observer<ArrayList<Genre>> { androidList ->
            recyclerView.adapter = GenresAdapter(this@MainActivity, androidList as ArrayList<Genre>, object : ItemClickListener {
                override fun onItemClick(pos: Int) {
                    intent = Intent(applicationContext, MoviesActivity::class.java)
                    intent.putExtra("id", androidList[pos].id)
                    startActivity(intent)
                }
            })
        })
    }
}
