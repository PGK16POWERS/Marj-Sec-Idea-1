package com.example.marjella_security_idea_1.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
object retrofitClient {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // Time to establish connection
        .readTimeout(30, TimeUnit.SECONDS)    // Time to read response
        .writeTimeout(30, TimeUnit.SECONDS)   // Time to send request
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.101.249:7600/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}