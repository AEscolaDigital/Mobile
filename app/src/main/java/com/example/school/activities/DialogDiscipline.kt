package com.example.school.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.example.school.api.school.ApiSchool
import com.example.school.models.Discipline
import com.example.school.models.Task
import retrofit2.Call
import java.util.*


class DialogDiscipline : AppCompatActivity() {

    lateinit var submit:Button
    lateinit var discipline_title: EditText
    lateinit var spinner_class:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_displine)

        submit = findViewById(R.id.submit)
        discipline_title = findViewById(R.id.discipline_title)
        spinner_class = findViewById(R.id.spinner_class)

        submit.setOnClickListener {
            Log.i("XPTO","XPTO CARA")
        }

    }

}