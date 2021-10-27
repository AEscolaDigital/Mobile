package com.example.school.api.school

import com.google.gson.Gson

data class School(
    var name :String,
    var phone : String,
    var name_school : String,
    var cnpj :String,
    var school_size : String,
    var address : Gson,
    var cep : String,
    var street: String,
    var district: String,
    var number: String,
    var complement: String,
    var city: String,
    var state: String,
    var uf_sate: String,
    var email: String,
    var password:String
    /*{
    //This is a request body expect by back end

        "name": "Matheus",
        "phone": "(11)9999-9999",
        "name_school": "Escola X",
        "cnpj": "97.042.598/0001-13",
        "school_size": "1",
        "address": [{
            "cep": "06663-895",
            "street": "Rua Abobrinha",
            "district": "Jardim dos Jardim",
            "number": "350",
            "complement": "Nenhum",
            "city": "Jandira",
            "state": "São Paulo",
            "uf_state": "SP"
        }],
        "email": "samuel@gmail15.com",
        "password": "123456"
    }*/
)