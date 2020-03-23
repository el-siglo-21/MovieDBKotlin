package com.example.moviedbkotlin.Models

import com.google.gson.annotations.SerializedName

class Genre {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
}