package com.example.school.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.adapter.DashboardAdapter
import com.example.school.api.school.ApiSchool
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
            val view = View.inflate(context, R.layout.dialog_displine, null)

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
                abrirGaleria()
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
            override fun onResponse(
                call: Call<List<Discipline>>,
                response: Response<List<Discipline>>
            ) {
                /*Log.i("RESPONSE body", response.body().toString())
                Log.i("RESPONSE", response.message().toString())
                Log.i("RESPONSE", response.code().toString())
                Log.i("RESPONSE", response.errorBody().toString())
                Log.i("RESPONSE", response.isSuccessful.toString())
                Log.i("RESPONSE", response.headers().toString())
                Log.i("RESPONSE", response.raw().toString())*/

                if (response.code() == 200) {
                    dashBoardAdapter.updateListasDisciplina(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Discipline>>, t: Throwable) {
                /*Log.i("REQUEST", "FAIL")*/
            }
        })

    }

    @Suppress("DEPRECATION")
    private fun abrirGaleria() {

        // Chamando a galeria de imagens
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // definir qual tipo de conteúdo deverá ser obtido
        intent.type = "image/*"

        // Iniciar a Activity, mas neste caso nós queremos que
        // esta Activity retorne algo para gente, a imagem
        this.startActivityForResult(
            Intent.createChooser(
                intent,
                "Escolha uma foto"
            ),
            CODE_IMAGE
        )
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == -1) {

            // Recuperar a imagem na stream
            val stream = contentResolver.openInputStream(data!!.data!!)

            // Transformar o resultado emn um BitMap
            imageBitMap = BitmapFactory.decodeStream(stream)

            // Colocar a imgaem no ImageView
            image_discipline.setImageBitmap(imageBitMap)

        }
    }

}