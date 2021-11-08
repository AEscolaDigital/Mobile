package com.example.school.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Remover a AppBar
        supportActionBar!!.hide()

        textViewCreateNewUser = findViewById(R.id.text_view_create_new_user)
        edit_email = findViewById(R.id.edit_email)
        edit_password = findViewById(R.id.edit_password)

        button = findViewById(R.id.button)

        layout_email = findViewById(R.id.inputLayoutEmail)
        layout_password = findViewById(R.id.inputLayoutPassword)



        button.setOnClickListener{
            login()
        }

        textViewCreateNewUser.setOnClickListener {
            openCreateNewUser()
        }
    }

    private fun login (){
        if(validate()){
            val role = "ROLE_ADMIN"
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()

            val login = Login(role, email, password)

            val remote = ApiSchool.SchoolEndPoint().sessionsService()

            val call: Call<Login> = remote.login(login)
//samuel.a.goulart@gmail.com
// 123456
            call.enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {

                    val user = response
                    if(user.code() == 403){
                        return Toast.makeText(applicationContext, "USUÁRIO OU SENHA INCORRETOS", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext, "Login concluido", Toast.LENGTH_LONG).show()
                        Log.i("XPTO", "Sucesso ao fazer login")
                        openDashboard()

                        Log.d("CODE", user.code().toString())
                        Log.d("XPTO", user.body().toString())
                        Log.d("MENSAGEM", user.message())
                    }

                }

                override fun onFailure(call: Call<Login>, error: Throwable) {
                    Toast.makeText(applicationContext, "Erro ao fazer login!", Toast.LENGTH_LONG).show()
                    Log.i("XPTO", error.message.toString())
                    /*Log.i("XPTO", "Erro ao fazer login")*/
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
            edit_email.error = "Campo obrigatorio"
        }
        if(edit_password.text.isEmpty()){
            var error = false
            edit_password.error = "Campo necessario"
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