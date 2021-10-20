package com.example.school

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SchoolRegistrationActivity : AppCompatActivity() {

    lateinit var editTextCep: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_registration)

        supportActionBar!!.title = "Cadastro de escola"
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_background))

        editTextCep = findViewById(R.id.edit_text_cep)
        Toast.makeText(this, "AAAAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show()


        if (editTextCep.length() == 8){
            Toast.makeText(this, "AAAAAAAAAAAAAAAAFFFFFFFFFA", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_create_school, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_save ->{
                return true
            }
            R.id.menu_cancel ->{
                Toast.makeText(this, "Cancel", Toast.LENGTH_LONG)
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }



}