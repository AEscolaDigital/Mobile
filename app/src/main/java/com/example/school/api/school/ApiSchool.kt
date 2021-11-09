package com.example.school.api.school

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class ApiSchool {

    interface ApiService {
        @POST("school/")
        fun register(@Body body: JsonObject): Call<JsonObject>
    }

    class SchoolEndPoint {
        val url = "http://localhost:3333/schools"
        val service = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        fun getService(): ApiService {
            return service.create(ApiService::class.java)
        }
    }
}