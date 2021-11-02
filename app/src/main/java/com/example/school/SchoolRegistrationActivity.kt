package com.example.school

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school.api.cep.RetrofitFactory
import com.example.school.api.cep.Cep
import com.example.school.api.school.Adress
import com.example.school.api.school.ApiSchool
import com.example.school.api.school.School
import com.example.school.utlis.MaskFormatUtil
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolRegistrationActivity : AppCompatActivity() {
    lateinit var textInputLayoutCep: TextInputLayout
    lateinit var textInputLayoutStreet: TextInputLayout
    lateinit var textInputLayoutPhone: TextInputLayout
    lateinit var textInputLayoutName: TextInputLayout
    lateinit var textInputLayoutCompanyName: TextInputLayout
    lateinit var textInputLayoutCNPJ: TextInputLayout
    lateinit var textInputLayoutNumber: TextInputLayout
    lateinit var textInputLayoutEmail: TextInputLayout


    lateinit var edit_name: EditText
    lateinit var editTextPhone: EditText
    lateinit var editTextCompanyName: EditText
    lateinit var editTextCnpj: EditText
    lateinit var editTextSchoolSize: EditText
    lateinit var editTextCep: EditText
    lateinit var editTextStreet: EditText
    lateinit var editTextDistrict: EditText
    lateinit var editTextCity: EditText
    lateinit var editTextUf: EditText
    lateinit var editTextComplement: EditText
    lateinit var editTextAdressNuber: EditText
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_registration)


        supportActionBar!!.title = "Cadastro de escola"
        supportActionBar!!.setBackgroundDrawable(getDrawable(R.drawable.toolbar_background))

        textInputLayoutPhone = findViewById(R.id.text_input_layout_phone)
        textInputLayoutCep = findViewById(R.id.text_input_layout_cep)
        textInputLayoutStreet = findViewById(R.id.text_input_layout_street)
        textInputLayoutName = findViewById(R.id.text_input_layout_name)
        textInputLayoutCNPJ = findViewById(R.id.text_input_layout_cnpj)
        textInputLayoutNumber = findViewById(R.id.text_input_layout_number)
        textInputLayoutEmail = findViewById(R.id.text_input_layout_email)


        edit_name = findViewById(R.id.text_input_edit_text_name)
        editTextPhone = findViewById(R.id.text_input_edit_text_phone)
        editTextCnpj = findViewById(R.id.text_input_edit_cnpj)
        editTextSchoolSize = findViewById(R.id.text_input_edit_shool_size)
        editTextCompanyName = findViewById(R.id.text_input_edit_text_company_name)
        editTextCep = findViewById(R.id.text_input_edit_cep)
        editTextStreet = findViewById(R.id.text_input_edit_street)
        editTextDistrict = findViewById(R.id.text_input_edit_district)
        editTextAdressNuber = findViewById(R.id.text_input_edit_adressNuber)
        editTextCity = findViewById(R.id.text_input_edit_city)
        editTextUf = findViewById(R.id.text_input_edit_uf)
        editTextComplement = findViewById(R.id.text_input_edit_complement)
        editTextEmail = findViewById(R.id.edit_text_email)
        editTextPassword = findViewById(R.id.edit_text_password)


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

    //Check if is online inputs filled
    private fun validaForm() : Boolean {
        var error = false

        if(edit_name.text.isEmpty()){
            textInputLayoutName.error = "Digite o seu nome!"
            error = true
        }
        if (editTextPhone.text.isEmpty()){
            textInputLayoutPhone.error = "Digite o telefone!"
            error = true
        }
        if (editTextCompanyName.text.isEmpty()){
            textInputLayoutCompanyName.error = "Digite o nome da Escola"
            error = true
        }
        if (editTextCnpj.text.isEmpty()){
            textInputLayoutCNPJ.error = "Digite o CNPJ da empresa"
            error = true
        }
        if(editTextCep.text.isEmpty()){
            textInputLayoutCep.error = "Digite o CEP"
            error = true
        }
        if(editTextAdressNuber.text.isEmpty()){
            textInputLayoutNumber.error = "Digite o número"
            error = true
        }
        if(editTextEmail.text.isEmpty()){
            textInputLayoutEmail.error = "Digite um email"
            error = true
        }

        if(editTextPassword.text.isEmpty() ){
            textInputLayoutNumber.error = "Digite uma senha"
            error = true;
        }

        return error
    }

    private fun save() {
        if (validaForm()){
            Toast.makeText(this, "Verifique se todos os campos estão preenchidos corretamente!", Toast.LENGTH_LONG).show()
        }else{
            val name = edit_name.text.toString()
            val phone = editTextPhone.text.toString()
            val cnpj = editTextCnpj.text.toString()
            val school_size = editTextSchoolSize.text.toString()
            val cep = editTextCep.text.toString()
            val street = editTextStreet.text.toString()
            val district = editTextDistrict.text.toString()
            val adressNumber = editTextAdressNuber.text.toString()
            val city = editTextCity.text.toString()
            val uf = editTextUf.text.toString()
            val complement = editTextComplement.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val companyName = editTextCompanyName.text.toString()

            val adress:Adress = Adress(cep, street, district, adressNumber, complement, city, "state", uf)
            val school:School = School(name,phone, companyName, cnpj, school_size, adress,email, password)

            val remote = ApiSchool.SchoolEndPoint().getService()

            val call: Call<School> = remote.register(school)


            call.enqueue(object : Callback<School> {
                override fun onResponse(call: Call<School>, response: Response<School>) {
                    Toast.makeText(applicationContext, "Escola cadastrada com sucesso!", Toast.LENGTH_LONG).show()
                    Log.i("XPTO", "Escola cadastrada com sucesso")
                    Log.i("XPTO", response.message().toString())
                }

                override fun onFailure(call: Call<School>, error: Throwable) {
                    Toast.makeText(applicationContext, "Erro ao cadastrar escola!", Toast.LENGTH_LONG).show()
                    Log.i("XPTO", error.message.toString())
                    Log.i("XPTO", "Erro ao cadastrar escola")
                }
            })
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