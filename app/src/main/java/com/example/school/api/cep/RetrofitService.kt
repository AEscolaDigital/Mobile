package com.example.school.api.cep

import com.example.school.models.Cep
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @GET("{CEP}/json/")
    fun getCEP(@Path("CEP") cep: String) :  Call<Cep>

    @GET("{uf}/{cidade}/{logradouro}/json")
    fun getCEPByLogradouro(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("logradouro")logradouro: String) : Call<List<Cep>>
}