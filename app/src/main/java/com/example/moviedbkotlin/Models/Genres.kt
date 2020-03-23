package com.example.moviedbkotlin.Models

import com.google.gson.annotations.SerializedName

class Genres {
    @SerializedName("genres")
    var genres = ArrayList<Genre>()
}