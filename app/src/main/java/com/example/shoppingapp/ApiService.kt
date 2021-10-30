package com.example.shoppingapp

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import com.example.shoppingapp.Product as Product1

interface ApiService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("bestRated")

    fun getProducts(
//        @Query("offset") offset: Int,
//        @Query("limit") limit: Int
    ): retrofit2.Call<List<Product1>>
}

