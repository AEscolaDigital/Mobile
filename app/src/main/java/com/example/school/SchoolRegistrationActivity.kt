package com.example.school

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitviacep.RetrofitFactory
import com.example.school.api.Cep
import com.example.school.utlis.MaskFormatUtil
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolRegistrationActivity : AppCompatActivity() {

    lateinit var textInputLayoutCep: TextInputLayout
    lateinit var textInputLayoutStreet: TextInputLayout
    lateinit var textInputLayoutPhone: TextInputLayout


    lateinit var editTextPhone: EditText
    lateinit var editTextCnpj: EditText
    lateinit var editTextCep: EditText
    lateinit var editTextStreet: EditText
    lateinit var editTextDistrict: EditText
    lateinit var editTextCity: EditText
    lateinit var editTextUf: EditText
    lateinit var editTextComplement: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_registration)


        supportActionBar!!.title = "Cadastro de escola"
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_background))

        textInputLayoutPhone = findViewById(R.id.text_input_layout_phone)
        textInputLayoutCep = findViewById(R.id.text_input_layout_cep)
        textInputLayoutStreet = findViewById(R.id.text_input_layout_street)

        editTextPhone = findViewById(R.id.text_input_edit_text_phone)
        editTextCnpj = findViewById(R.id.text_input_edit_cnpj)
        editTextCep = findViewById(R.id.text_input_edit_cep)
        editTextStreet = findViewById(R.id.text_input_edit_street)
        editTextCity = findViewById(R.id.text_input_edit_city)
        editTextUf = findViewById(R.id.text_input_edit_uf)
        editTextDistrict = findViewById(R.id.text_input_edit_district)
        editTextComplement = findViewById(R.id.text_input_edit_complement)


        editTextCep.setOnFocusChangeListener { v, hasFocus ->

            val cep = MaskFormatUtil.unmask(editTextCep.text.toString())

            if (!hasFocus && cep.length == 8) {
                searchByCEP()
                textInputLayoutCep.error = null
            }

            if (!hasFocus && cep.length < 8) {
                textInputLayoutCep.error = "CEP inválido"
            }
        }

        editTextPhone.addTextChangedListener(
            MaskFormatUtil.mask(
                editTextPhone,
                MaskFormatUtil.FORMAT_FONE_COD_AREA
            )
        )
        editTextCnpj.addTextChangedListener(
            MaskFormatUtil.mask(
                editTextCnpj,
                MaskFormatUtil.FORMAT_CNPJ
            )
        )
        editTextCep.addTextChangedListener(
            MaskFormatUtil.mask(
                editTextCep,
                MaskFormatUtil.FORMAT_CEP
            )
        )

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_create_school, menu)
        return true
    }

    private fun searchByCEP() {

        val textInputLayout = textInputLayoutCep.editText!!.text

        val remote = RetrofitFactory().retrofitService()
        val call: Call<Cep> = remote.getCEP(textInputLayout.toString())

        call.enqueue(object : Callback<Cep> {
            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                val cep = response.body()
                if (cep != null) {
                    editTextStreet.setText(cep.logradouro)
                    editTextCity.setText(cep.cidade)
                    editTextUf.setText(cep.uf)
                    editTextDistrict.setText(cep.bairro)
                    editTextComplement.setText(cep.complemento)
                }
            }

            override fun onFailure(call: Call<Cep>, t: Throwable) {
                Log.i("cep", t.message.toString())
            }

        })
    }

    private fun validaForm() : Boolean {

        var error = false

        if (editTextPhone.text.isEmpty()){
            textInputLayoutPhone.error = "Digite o telefone!"
            error = true
        }

        return error
    }

    private fun save() {
        if (validaForm()){
            Toast.makeText(this, "Verifique se todos os campos estão preenchidos corretamente!", Toast.LENGTH_LONG).show()
        }
    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
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