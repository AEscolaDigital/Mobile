package com.example.school.models

import retrofit2.Call

data class Discipline (
    var id: Int,
    var sigla: String,
    var teacher_name: String,
    var image : String,
)

