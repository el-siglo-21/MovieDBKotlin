package com.example.moviedbkotlin.Interfaces

import com.example.moviedbkotlin.Models.Response
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String, @Query("page") page: Int, @Query("with_genres") with_genres: String): Single<Response>

    companion object {
        fun getService(): MoviesService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(MoviesService::class.java)
        }
    }
}