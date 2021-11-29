package com.example.school.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.example.school.api.school.ApiSchool
import com.example.school.models.Discipline
import com.example.school.models.Task
import retrofit2.Call
import java.util.*


class DialogTask : AppCompatActivity() {

    lateinit var btn_finalizar:Button

    lateinit var edNome: EditText
    lateinit var edDescription: EditText
    lateinit var edData: EditText
    lateinit var edPontuacao : EditText
    lateinit var edAnexo : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_task)

        btn_finalizar= findViewById(R.id.btn_finalizar)

        edNome = findViewById(R.id.ed_nome_task)
        edDescription = findViewById(R.id.ed_descricao_task)
        edData = findViewById(R.id.ed_data)
        edPontuacao = findViewById(R.id.ed_pontuacao)
        edAnexo = findViewById(R.id.ed_anexo)


//        val task: Task = Task()
//        task.id = 1
//        task.description = "I do not  know"
//        task.attachment = "Vai se ..."
//        task.deliveryDate = Date()
//        task.title = "Consumir API "
//        task.punctuation = 20


        btn_finalizar.setOnClickListener{
            Log.i("XPTO", "BATEU NO BOTAO TASK SUBMIT")
        }

        validate()

    }

    private fun validate():Boolean{
        var error = false

        if(edNome.text.isEmpty()){
            edNome.error = "Campo necessário"
            error = true
        }

        if (edData.text.isEmpty()){
            edData.error =  "Campo necessário"
            error = true
        }

        return error
    }



}