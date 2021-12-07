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



class ViewTaskActivity : AppCompatActivity() {

    lateinit var frameLayout: FrameLayout
    lateinit var arrow_back: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        // Remover a AppBar
        supportActionBar!!.hide()

        frameLayout = findViewById(R.id.frameLayout_bottomsheet)
        arrow_back = findViewById(R.id.arrow_back)


        BottomSheetBehavior.from(frameLayout).apply {
            peekHeight=200
            this.state=BottomSheetBehavior.STATE_COLLAPSED
        }

        arrow_back.setOnClickListener {
            onBackPressed()
        }

    }




}