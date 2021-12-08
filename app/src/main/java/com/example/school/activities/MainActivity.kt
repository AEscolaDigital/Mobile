package com.example.school.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.school.R
import androidx.fragment.app.Fragment
import com.example.school.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var perfil_usuario: ImageView
    var imageBitMap: Bitmap? = null

    lateinit var bottom_navigation: BottomNavigationView

    private val teamsFragment = TeamsFragment()
    private val taskFragment = TaskFragment()
    private val callFragment = GradesFragment()

    //Consts
    val CODE_IMAGE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_fragments)


        // Remover a AppBar
        supportActionBar!!.hide()

        perfil_usuario = findViewById(R.id.perfil_usuario)

        bottom_navigation = findViewById(R.id.navegation_bottom)
        //set bottom navigation default item selected
        bottom_navigation.selectedItemId = R.id.ic_home
        //set teamsFragment per default
        replaceFragment(teamsFragment)

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

                R.id.ic_grades -> {
                    replaceFragment(callFragment)
                    true
                }
            }
            true
        }



        fun dialogImagem() {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("ALterar Foto")
            alert.setMessage("Você deseja altera sua foto de perfil ?")
            alert.setPositiveButton("Sim", { dialogInterface: DialogInterface, i: Int ->
                getImageFromGallery()
            })
            alert.setNegativeButton("Agora não", { dialogInterface: DialogInterface, i: Int ->
                
            })
            alert.show()
        }

        perfil_usuario.setOnClickListener {
            dialogImagem()
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun getImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, CODE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == RESULT_OK) {
            // Pegando a imagem da galeria
            val imageURI = data!!.data

            // Pegando a imagem do URI
            val imageBitmap =
                BitmapFactory.decodeStream(this.contentResolver?.openInputStream(imageURI!!))

            // Setando a imagem no ImageView
            perfil_usuario.setImageBitmap(imageBitmap)

            // Salvando a imagem no ImageView
            imageBitMap = imageBitmap
        }
    }
}


