package com.example.school.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.school.R


lateinit var btn_criar_disciplina: Button


class TeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)


        btn_criar_disciplina = findViewById(R.id.btn_criar_disciplina)


        btn_criar_disciplina.setOnClickListener{

           val view = View.inflate(this, R.layout.dialog_displine, null)

            val builder = AlertDialog.Builder(this)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        // Remover a AppBar
        supportActionBar!!.hide()
    }
}