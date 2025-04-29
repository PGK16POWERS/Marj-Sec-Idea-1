package com.example.marjella_security_idea_1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object retrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.101.250:7600/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}