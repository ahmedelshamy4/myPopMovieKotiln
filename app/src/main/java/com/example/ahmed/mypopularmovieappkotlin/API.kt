package com.example.ahmed.mypopularmovieappkotlin

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface API {
    @GET("popular?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getPopular(): Call<MovieResponse>

    @GET("top_rated?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getTopRated(): Call<MovieResponse>

    @GET("{id_movie}/videos?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getTrailers(@Path("id_movie") idMovie: String): Call<TrailerResponse>

    @GET("{id_movie}/reviews?api_key=a619ffd371a3fc63c02faefe0478df7d")
    fun getReviews(@Path("id_movie") idMovie: String): Call<ReviewResponse>
}


