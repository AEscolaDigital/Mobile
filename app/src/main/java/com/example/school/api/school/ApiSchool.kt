package com.example.school.api.school

import com.example.school.Login
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


class ApiSchool {

    interface ApiService {
        @POST("schools/")
        fun register(@Body body: School): Call<School>
    }

    interface Login{
        @POST("/sessions")
        fun login(@Body body: com.example.school.Login): Call<com.example.school.Login>
    }

    class SchoolEndPoint {
        val url = "http://10.107.144.27:3333/"
        val service = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        fun getService(): ApiService {
            return service.create(ApiService::class.java)
        }

        fun loginService(): Login{
            return service.create(Login::class.java)
        }
    }
}