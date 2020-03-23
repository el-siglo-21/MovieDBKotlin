package com.example.moviedbkotlin.Interfaces

import com.example.moviedbkotlin.Models.Genres
import com.example.moviedbkotlin.Models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") api_key: String, @Query("language") language: String): Call<Genres>
    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String, @Query("page") page: Int, @Query("with_genres") with_genres: String): Call<Movies>
}