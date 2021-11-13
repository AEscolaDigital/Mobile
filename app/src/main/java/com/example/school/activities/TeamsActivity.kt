package com.example.school.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.school.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school.adapter.DashboardAdapter
import com.example.school.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeamsActivity : AppCompatActivity() {

    lateinit var bottom_navigation: BottomNavigationView
    lateinit var recyclerViewTurmas: RecyclerView

    private val teamsFragment = TeamsFragment()
    private val taskFragment = TaskFragment()
    private val memberFragment = MemberFragment()
    private val callFragment = CallFragment()
    private val presencaFragment = PresencaFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        //set teamsFragment per default
        replaceFragment(teamsFragment)

        // Remover a AppBar
        supportActionBar!!.hide()


        bottom_navigation = findViewById(R.id.navegation_bottom)

        // Fazendo a troca de tela ao clicar em um icone no bottom_navigation
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_home -> {
                    replaceFragment(teamsFragment)
                    true
                }
                R.id.ic_tarefas -> {
                    replaceFragment(taskFragment)
                    true
                }
                R.id.ic_addMembros -> {
                    replaceFragment(memberFragment)
                    true
                }

                R.id.ic_chamada -> {
                    replaceFragment(callFragment)
                    true
                }

                R.id.ic_presenca -> {
                    replaceFragment(presencaFragment)
                    true
                }
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}


