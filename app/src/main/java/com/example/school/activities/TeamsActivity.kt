package com.example.school.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.school.R

class TeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)


        // Remover a AppBar
        supportActionBar!!.hide()
    }
}