package com.example.school

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class SchoolRegistrationActivity : AppCompatActivity() {

    lateinit var textInputLayoutCep: TextInputLayout
    lateinit var editTextCep: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_registration)

        supportActionBar!!.title = "Cadastro de escola"
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_background))

         textInputLayoutCep = findViewById(R.id.text_input_layout_cep)
        // val editTextCep = textInputLayoutCep.editText!!.text

        editTextCep = findViewById(R.id.edit_text_cep)

        editTextCep.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus){
                openLoginActivity()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_create_school, menu)
        return true
    }

    private fun save(){
        textInputLayoutCep = findViewById(R.id.text_input_layout_cep)
        val text = textInputLayoutCep.editText!!.text

      Toast.makeText(this, "${text.toString().length}", Toast.LENGTH_SHORT).show()

    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_save -> {
                save()
                return true
            }
            R.id.menu_cancel -> {
                openLoginActivity()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }



}