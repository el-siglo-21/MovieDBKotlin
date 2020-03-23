package com.example.moviedbkotlin.Models

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("page")
    var page: Int = 0
    @SerializedName("total_results")
    var total_results: Int = 0
    @SerializedName("total_pages")
    var total_pages: Int = 0
    @SerializedName("results")
    var results = ArrayList<Movie>()
}