package com.example.retrofitviacep

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitFactory {
    val URL = "https://viacep.com.br/ws/"

    val retrofitFactory =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun retrofitService() : RetrofitService{
        return retrofitFactory.create(RetrofitService::class.java)
    }
}