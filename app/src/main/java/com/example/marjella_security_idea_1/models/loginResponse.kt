package com.example.marjella_security_idea_1.models

data class LoginResponse(
    val status: Int,
    val message: String,
    val cryticEmail: String
)
