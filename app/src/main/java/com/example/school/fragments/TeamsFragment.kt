package com.example.school.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.adapter.DashboardAdapter
import com.example.school.api.school.ApiSchool
import com.example.school.models.Class
import com.example.school.models.Discipline
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsFragment : Fragment() {
    private lateinit var recyclerViewTurmas: RecyclerView
    private lateinit var dashBoardAdapter: DashboardAdapter
    lateinit var btn_criar_disciplina: Button

    var imageBitMap: Bitmap? = null

    //Dialog
    lateinit var image_discipline: ImageView
    lateinit var submit: Button
    lateinit var discipline_title: EditText
    lateinit var spinner_class: Spinner

    //Consts
    val CODE_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val context = view.context

        btn_criar_disciplina = view.findViewById(R.id.btn_criar_disciplina)

        recyclerViewTurmas = view.findViewById(R.id.recycler_teams)!!
        dashBoardAdapter = DashboardAdapter(context)
        //layout of recyclerview, grid two columns
        recyclerViewTurmas.layoutManager = GridLayoutManager(context, 2)
        //LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        //* Definindo a Adapter da RV(RecycleView)
        recyclerViewTurmas.adapter = dashBoardAdapter

        btn_criar_disciplina.setOnClickListener {
            val view:View = View.inflate(context, R.layout.dialog_displine, null)

            val builder = AlertDialog.Builder(context)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            submit = view.findViewById(R.id.submit)
            discipline_title = view.findViewById(R.id.discipline_title)
            spinner_class = view.findViewById(R.id.spinner_class)
            image_discipline = view.findViewById(R.id.image_discipline)


            submit.setOnClickListener {
                Log.i("XPTO", "criar disciplinas")
            }

            image_discipline.setOnClickListener {
                getImageFromGallery()
            }

        }


        //Set btn_criar_disciplina INVISIBLE
        //btn_criar_disciplina.setVisibility(View.INVISIBLE)

        //call the shared preferences school and get jwt
        val sharedPreferences = context.getSharedPreferences("school", 0)
        val jwt = sharedPreferences.getString("JWT", "teste de chamada saida vazia")

        //call the api for populate disciplines of studant
        val remote = ApiSchool.SchoolEndPoint().dashboardService()
        val call: Call<List<Discipline>> = remote.listDisciplines("Bearer $jwt")

        //aply a request async and get the response
        call.enqueue(object : Callback<List<Discipline>> {
            override fun onResponse(call: Call<List<Discipline>>,response: Response<List<Discipline>>) {
                if (response.code() == 200) {
                    dashBoardAdapter.updateListasDisciplina(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Discipline>>, t: Throwable) {
                /*Log.i("REQUEST", "FAIL")*/
            }
        })


        val remoteTwo = ApiSchool.SchoolEndPoint().classesService()
        val callTwo: Call<Class> = remoteTwo.listClasses("Bearer $jwt")

        callTwo.enqueue(object : Callback<Class> {
            override fun onResponse(call: Call<Class>, response: Response<Class>) {
                /*if (response.code() == 200) {
                    populateSpinner(context, response.body())
                }*/
                Log.i("XPTO", "Chamada na API")
                Log.i("XPTO", response.body().toString())
                Log.i("XPTO", response.errorBody().toString())
                Log.i("XPTO", response.code().toString())
                Log.i("XPTO", response.raw().toString())
                Log.i("XPTO", "b")
            }

            override fun onFailure(call: Call<Class>, t: Throwable) {
                /*Log.i("REQUEST", "FAIL")*/
                Log.i("XPTO",t.message.toString())
                Log.i("XPTO",t.cause.toString())
            }
        })

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
                BitmapFactory.decodeStream(context?.contentResolver?.openInputStream(imageURI!!))

            // Setando a imagem no ImageView
            image_discipline.setImageBitmap(imageBitmap)

            // Salvando a imagem no ImageView
            imageBitMap = imageBitmap
        }
    }

}