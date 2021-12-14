package com.example.school.models

data class schoolRegisterResponse (
    @Suppress("id")
    var schoolid: String,
    var name: String,
    var email: String,
    var role: String,
    var token: String
)