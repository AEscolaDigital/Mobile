package com.example.school.api.school

import com.example.school.models.School
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


class ApiSchool {

    interface ApiService {
        @POST("schools/")
        fun register(@Body body: School): Call<School>
    }

    interface Sessions{
        @POST("/sessions")
        fun login(@Body body: com.example.school.models.Login): Call<com.example.school.models.Login>
    }

    class SchoolEndPoint {
        val url = "http://10.0.0.100:3333/"
        val service = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        fun getService(): ApiService {
            return service.create(ApiService::class.java)
        }

        fun sessionsService(): Sessions{
            return service.create(Sessions::class.java)
        }
    }
}
