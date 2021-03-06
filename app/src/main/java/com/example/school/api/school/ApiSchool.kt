package com.example.school.api.school

import com.example.school.models.Class
import com.example.school.models.Discipline
import com.example.school.models.School
import com.example.school.models.schoolRegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


class ApiSchool {
    //How to set a body and receive another
    /*interface ApiService {
        @POST("schools")
        fun register(@Body body: School): Call<Discipline>
    }*/
    interface ApiService {
        @POST("schools")
        fun register(@Body body: School): Call<schoolRegisterResponse>
    }

    interface Sessions {
        @POST("/sessions")
        fun login(@Body body: com.example.school.models.Login): Call<com.example.school.models.Login>
    }

    interface Discipline {
        @GET("/disciplines")
        fun listDisciplines(@Header("Authorization") token: String): Call<List<com.example.school.models.Discipline>>

        @Multipart
        @POST("/disciplines")
        fun createDiscipline(@Header("Authorization") token: String,
                             @Part(value = "name") name: String,
                             @Part(value = "sigla") sigla: String,
                             @Part(value = "class_id") clas_id: String,
                             @Part image: MultipartBody.Part,): Call<com.example.school.models.Discipline>
    }

    interface classes {
        @GET("/classes/1")
        fun listClasses(@Header("Authorization") token: String): Call<Class>
    }

    interface Task {
        @GET("/tasks/list/1")
        fun listTask(@Header("Authorization") token: String): Call<List<com.example.school.models.TaskList>>
    }

    class SchoolEndPoint {

        val url = "https://educ-tec-back-end.herokuapp.com/"
        //val url = "http://10.0.0.104:3333/"

        val service =
            Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build()

        fun getService(): ApiService {
            return service.create(ApiService::class.java)
        }

        fun sessionsService(): Sessions {
            return service.create(Sessions::class.java)
        }

        fun dashboardService(): Discipline {
            return service.create(Discipline::class.java)
        }

        fun taskService(): Task {
            return service.create(Task::class.java)
        }

        fun classesService(): classes {
            return service.create(classes::class.java)
        }
    }
}

