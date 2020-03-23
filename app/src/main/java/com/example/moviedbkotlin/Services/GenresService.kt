package com.example.moviedbkotlin.Services

import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import com.example.moviedbkotlin.Interfaces.APIService
import com.example.moviedbkotlin.Models.Genre
import com.example.moviedbkotlin.Models.Genres
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GenresService {
    var genresData: MutableLiveData<ArrayList<Genre>> = MutableLiveData()

    companion object Factory {
        var gson = GsonBuilder().setLenient().create();
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.themoviedb.org/3/")
                .build()
            return retrofit.create(APIService::class.java)
        }
    }

    fun loadGenresData(): MutableLiveData<ArrayList<Genre>>? {
        val call = create().getGenres("668db373e36ebd9085d4685d02318231", "en-US")
        call.enqueue(object : Callback<Genres> {
            override fun onFailure(call: Call<Genres>, t: Throwable) {

            }

            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                if (response.isSuccessful) {
                    val list = response.body()?.genres
                    genresData?.value = list
                }
            }
        })
        return genresData
    }
}