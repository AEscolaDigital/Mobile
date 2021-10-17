package com.example.school

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var textViewCreateNewUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textViewCreateNewUser = findViewById(R.id.text_view_create_new_user)

        textViewCreateNewUser.setOnClickListener {
            openCreateNewUser()
        }
    }

    private fun openCreateNewUser() {
        val intent = Intent(this, newUserActivity::class.java)
        startActivity(intent)
        finish()
    }
}