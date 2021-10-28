//package com.example.school.api.school
//
//import android.util.Log
//import com.google.gson.JsonObject
//import com.google.gson.JsonParser
//import org.json.JSONException
//import org.json.JSONObject
//import retrofit2.Call
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.Headers
//import retrofit2.http.POST
//
//
//class ApiSchool {
//
//    interface ApiService {
//        @Headers("Content-Type: application/json")
//        @POST("school/")
//        fun register(@Body body: JsonObject): Call<JsonObject>
//    }
//
//    class SchoolEndPoint {
//        val url = "http://localhost:3333/schools"
//        val service = RetrofitClient.baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
//
//        fun getService(): SchoolEndPoint {
//
//            return retrofitFactory.create(SchoolEndPoint::class.java)
//        }
//    }
//}