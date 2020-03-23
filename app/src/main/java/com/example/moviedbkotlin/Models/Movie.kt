package com.example.moviedbkotlin.Models

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @SerializedName("vote_count")
    var vote_count: Int = 0,
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("original_language")
    var original_language: String? = null,
    @SerializedName("original_title")
    var original_title: String? = null,
    @SerializedName("genre_ids")
    var genre_ids: ArrayList<Integer>,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var vote_average: Double = 0.0,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("release_date")
    var release_date: String? = null
)