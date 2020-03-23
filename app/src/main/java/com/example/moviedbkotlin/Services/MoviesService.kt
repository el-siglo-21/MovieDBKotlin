package com.example.moviedbkotlin.Services

import androidx.lifecycle.MutableLiveData
import com.example.moviedbkotlin.Interfaces.APIService
import com.example.moviedbkotlin.Models.Movies
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MoviesService(var page: Int, var with_genres: String) {
    var moviesData: MutableLiveData<Movies> = MutableLiveData()

    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.themoviedb.org/3/")
                .build()
            return retrofit.create(APIService::class.java)
        }
    }

    fun loadMoviesData(): MutableLiveData<Movies>? {
        val call = create().getMovies("668db373e36ebd9085d4685d02318231", page, with_genres)
        call.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {

            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    moviesData?.value = list
                }
            }
        })
        return moviesData
    }
}