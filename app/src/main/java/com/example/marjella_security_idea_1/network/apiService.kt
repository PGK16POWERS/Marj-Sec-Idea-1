package com.example.marjella_security_idea_1.network

import com.example.marjella_security_idea_1.models.CreateAccountResponse
import com.example.marjella_security_idea_1.models.Login
import com.example.marjella_security_idea_1.models.LoginResponse
import com.example.marjella_security_idea_1.models.createAccount
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/android/auth/login")
    suspend fun login(@Body login: Login): Response<LoginResponse>

    @POST("/android/auth/createAccount")
    suspend fun createAccount(@Body createAccount: createAccount): Response<CreateAccountResponse>
}