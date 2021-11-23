package com.example.school.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.example.school.api.school.ApiSchool
import com.example.school.models.Login
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var textViewCreateNewUser: TextView
    lateinit var edit_email: EditText
    lateinit var edit_password: EditText

    lateinit var layout_email : TextInputLayout
    lateinit var layout_password : TextInputLayout

    lateinit var radio_type_user: RadioGroup
    lateinit var button : Button

    private var roleUser:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Remover a AppBar
        supportActionBar!!.hide()

        textViewCreateNewUser = findViewById(R.id.text_view_create_new_user)
        edit_email = findViewById(R.id.edit_email)
        edit_password = findViewById(R.id.edit_password)

        layout_email = findViewById(R.id.inputLayoutEmail)
        layout_password = findViewById(R.id.inputLayoutPassword)

        radio_type_user = findViewById(R.id.type_user_login)
        button = findViewById(R.id.button)


        textViewCreateNewUser.setOnClickListener {
            openCreateNewUser()
        }

        radio_type_user.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_aluno_professor) {
                roleUser = "ROLE_USER"
            } else {
                roleUser = "ROLE_ADMIN"
            }
        }

        button.setOnClickListener{
            login()
        }

    }

    private fun login (){
        if(validate()){

            var login = Login()
            login.role = roleUser
            login.email = edit_email.text.toString()
            login.password = edit_password.text.toString()

            //Log.i("ASENHA",login.password)

            val remote = ApiSchool.SchoolEndPoint().sessionsService()
            val call: Call<Login> = remote.login(login)

            call.enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {

                    val user = response
                    if (user.code() == 403) {
                        return Toast.makeText(
                            applicationContext,
                            "USUÁRIO OU SENHA INCORRETOS",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(applicationContext, "Login concluido", Toast.LENGTH_LONG).show()
                        //This parte save the Jwt, email and key of user in shared Preference
                        val sharedPreferences = getSharedPreferences("school", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("JWT", user.body()?.token.toString())
                        editor.putString("EMAIL", user.body()?.user?.email.toString())
                        editor.putString("NAME", user.body()?.user?.name.toString())
                        editor.apply()

                        openDashboard()

                    }
                }

                override fun onFailure(call: Call<Login>, error: Throwable) {
                    Toast.makeText(applicationContext, "Erro ao fazer login!", Toast.LENGTH_LONG)
                        .show()
                    Log.i("XPTO", error.message.toString())
                }
            })

        }else{
            Toast.makeText(applicationContext, "Informações nescessarias", Toast.LENGTH_LONG).show()
        }

    }

    private fun validate():Boolean{
        var error = true

        if(edit_email.text.isEmpty()){
            error = false
            edit_email.error = "Campo necessário"
        }
        if(edit_password.text.isEmpty()){
            error = false
            edit_password.error = "Campo necessário"
        }

        return error
    }

    private fun openCreateNewUser() {
        val intent = Intent(this, SchoolRegistrationActivity::class.java)
        startActivity(intent)
    }

    private fun openDashboard() {
        val registerScreen = Intent(this, MainActivity::class.java)
        startActivity(registerScreen)
    }



}