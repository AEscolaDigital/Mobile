package com.example.school.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.example.school.api.school.ApiSchool
import com.example.school.models.Login
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val CODE_IMAGE = 100

class ViewTaskActivity : AppCompatActivity() {

    lateinit var frameLayout: FrameLayout
    lateinit var arrow_back: TextView
    lateinit var add_task_file: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)



        // Remover a AppBar
        supportActionBar!!.hide()

        frameLayout = findViewById(R.id.frameLayout_bottomsheet)
        arrow_back = findViewById(R.id.arrow_back)
        add_task_file = findViewById(R.id.btn_adicionar_tarefa)


        BottomSheetBehavior.from(frameLayout).apply {
            peekHeight=400
            this.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        arrow_back.setOnClickListener {
            onBackPressed()
        }

        add_task_file.setOnClickListener{
            pegarFiles()
        }
    }


    private fun pegarFiles() {

        // Chamando o storege de arquivos
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Definir qual o tipo de conteúdo deverá ser obtido
        intent.type = "application/pdf"

        // Iniciar a Activity, mas neste caso nós queremos que
        // esta Activity retorne algo pra gente, o arquivo
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Escolha um arquivo"),
            CODE_IMAGE)

    }




}