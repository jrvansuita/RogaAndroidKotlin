package com.example.rogaandroidkotlin.api

import com.example.rogaandroidkotlin.model.Comment
import com.example.rogaandroidkotlin.model.Post
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .baseUrl(BASE_URL)
    .build()


interface ApiService {
    @GET("/posts")
     fun getPosts(): Call<List<Post>>

    @GET("/posts/{id}/comments")
     fun getComments(@Path("id") id: Int): Call<List<Comment>>
}


object Api {
    val retrofitService: ApiService by lazy{retrofit.create(ApiService::class.java)}
}