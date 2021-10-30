package com.example.shoppingapp

import com.google.gson.annotations.SerializedName


//this is the entity for defining photos used
data class Photo(
    @SerializedName("filename")
    var filename: String? = null
)