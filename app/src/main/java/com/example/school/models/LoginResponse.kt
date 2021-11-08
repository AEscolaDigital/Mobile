package com.example.school.models

data class LoginResponse (
    val user: UserLogiResponse,
    val token:String
)