package com.example.school

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.school.fragments.TeamsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeamsActivity : AppCompatActivity() {

    lateinit var bottom_navigation : BottomNavigationView
    private val teamsFragment = TeamsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
        replaceFragment(teamsFragment)


    bottom_navigation = findViewById(R.id.navegation_bottom)

        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(teamsFragment)
                R.id.ic_tarefas -> replaceFragment(teamsFragment)
            }

            true
        }

        // Remover a AppBar
        supportActionBar!!.hide()

    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}


