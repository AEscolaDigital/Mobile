package com.example.school.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Adress(
    var cep : String,
    var street: String,
    var district: String,
    @SerializedName("number")
    var adressNumber: String,
    var complement: String,
    var city: String,
    var state: String,
    var uf: String
)
