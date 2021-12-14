package com.example.school.fragments

import android.Manifest
import android.Manifest.permission.MANAGE_EXTERNAL_STORAGE
import android.Manifest.permission.START_VIEW_PERMISSION_USAGE
import android.content.Context
import android.content.Intent
import android.content.RestrictionsManager
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.loader.content.CursorLoader
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.adapter.DashboardAdapter
import com.example.school.api.school.ApiSchool
import com.example.school.models.Class
import com.example.school.models.Discipline
import com.example.school.models.classe
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.MediaType.get
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.security.Permission


class TeamsFragment : Fragment() {

    private lateinit var recyclerViewTurmas: RecyclerView
    private lateinit var dashBoardAdapter: DashboardAdapter
    lateinit var btn_criar_disciplina: Button

    var imageBitMap: Bitmap? = null

    //Dialog
    lateinit var image_discipline: ImageView
    lateinit var submit: Button
    lateinit var discipline_title: EditText
    lateinit var discipline_initials:EditText
    lateinit var spinner_class: Spinner


    var imagePath: String = ""
    var imageBitmap: Bitmap? = null
    val CODE_IMAGE = 100

    //Consts
    //val CODE_IMAGE = 100
    var lista = emptyList<classe>()

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

    @RequiresApi(Build.VERSION_CODES.Q)
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
            
            val permissionStorageInfo =  ContextCompat.checkSelfPermission(context, "MANAGE_EXTERNAL_STORAGE")

            if(permissionStorageInfo == -1){
                activity?.requestPermissions(arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE), 100)
                activity?.requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
                Toast.makeText(context, "Permissão para acessar o armazenamento foi concedida", Toast.LENGTH_LONG).show()

            }

            val view:View = View.inflate(context, R.layout.dialog_displine, null)

            val builder = AlertDialog.Builder(context)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            submit = view.findViewById(R.id.submit)
            discipline_title = view.findViewById(R.id.discipline_title)
            discipline_initials = view.findViewById(R.id.discipline_initials)
            spinner_class = view.findViewById(R.id.spinner_class)
            image_discipline = view.findViewById(R.id.image_discipline)

            var classList:List<classe>


            submit.setOnClickListener {
                var comparableList = lista
                var position = spinner_class.selectedItemPosition
                var classId = comparableList[1]


                val file = File(imagePath)
                val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val body = MultipartBody.Part.createFormData("image", file.name, requestBody)

                if(position === 0){
                    (spinner_class.getSelectedView() as TextView).error = "Selecione!"
                }else{
                    Log.i("XPTO turma", classId.id)
                    Log.i("XPTO discipina", discipline_title.text.toString())
                    Log.i("XPTO sigla disciplina", discipline_initials.text.toString() )
                    Log.i("XPTO image", image_discipline.toString())
                    //get the image path from the imageView
                    imageBitMap = image_discipline.getDrawingCache()

                    var name = discipline_title.text.toString()
                    var sigla = discipline_initials.text.toString()
                    var classId = classId.id

                    createDiscipline(name,sigla,classId,body)

                }



            }


            image_discipline.setOnClickListener {

                abrirGaleria()
            }

            val sharedPreferences = context.getSharedPreferences("school", 0)
            val jwt = sharedPreferences.getString("JWT", "teste de chamada saida vazia")

            val remoteTwo = ApiSchool.SchoolEndPoint().classesService()
            val callTwo: Call<Class> = remoteTwo.listClasses("Bearer $jwt")


            callTwo.enqueue(object : Callback<Class> {
                override fun onResponse(call: Call<Class>, response: Response<Class>) {
                    /*if (response.code() == 200) {

                    }*/
                    classList = response.body()?.rows!!

                    lista = classList

                    var classNames = arrayOfNulls<String>(classList!!.size + 1)
                    classNames[0] = "Turma"
                    for (i in 0 until classList.size) {
                        var position = i + 1
                        classNames[position] = classList[i].sigla
                    }
                    val adapter = ArrayAdapter(
                        context,
                        android.R.layout.simple_spinner_item,
                        classNames
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner_class.adapter = adapter

                }

                override fun onFailure(call: Call<Class>, t: Throwable) {
                    Log.i("XPTO snpinner", t.message.toString())
                }
            })

        }


        //call the shared preferences school and get jwt
        val sharedPreferences = context.getSharedPreferences("school", 0)
        val jwt = sharedPreferences.getString("JWT", "teste de chamada saida vazia")
        val role = sharedPreferences.getString("ROLE", "")

        if(role == "ROLE_USER"){
            //Set btn_criar_disciplina INVISIBLE
            btn_criar_disciplina.setVisibility(View.INVISIBLE)
        }


        //call the api for populate disciplines of studant
        val remote = ApiSchool.SchoolEndPoint().dashboardService()
        val call: Call<List<Discipline>> = remote.listDisciplines("Bearer $jwt")

        //aply a request async and get the response
        call.enqueue(object : Callback<List<Discipline>> {
            override fun onResponse(
                call: Call<List<Discipline>>,
                response: Response<List<Discipline>>
            ) {
                if (response.code() == 200) {
                    dashBoardAdapter.updateListasDisciplina(response.body()!!)
                } else {
                    //TODO: error to load  disciplines
                }
            }

            override fun onFailure(call: Call<List<Discipline>>, t: Throwable) {
                /*Log.i("REQUEST", "FAIL")*/
            }
        })


    }

    private fun createDiscipline(name:String, sigla:String, classId:String, image: MultipartBody.Part) {
        val sharedPreferences = context?.getSharedPreferences("school", 0)
        val jwt = sharedPreferences?.getString("JWT", "teste de chamada saida vazia")

        val remote = ApiSchool.SchoolEndPoint().dashboardService()
        val call: Call<Discipline> = remote.createDiscipline("Bearer $jwt","", "", "",image)

        call.enqueue(object : Callback<Discipline> {
            override fun onResponse(call: Call<Discipline>,response: Response<Discipline>) {
                Log.i("Xpto create dis 1", response.body().toString())
                Log.i("Xpto create dis 2", response.message().toString())
                Log.i("Xpto create dis 3", response.headers().toString())
                Log.i("Xpto create dis 4", response.errorBody().toString())
                if (response.code() == 200) {
                    Toast.makeText(context, "Disciplina criada com sucesso!", Toast.LENGTH_LONG)
                        .show()
//                    dashBoardAdapter.updateListasDisciplina(response.body()!!)
                    //TODO: implement mensage for where discipline is created
                } else {
                    Toast.makeText(context, "Erro ao criar disciplina!", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<Discipline>, t: Throwable) {
                Log.i("REQUEST", "FAIL")
                Log.i("Xpto create dis 1", t.message.toString())
            }
        })
    }

    //image
    private fun createImageFile(fileName: String = "temp_image"): File {
        val storageDir = view?.context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image", ".jpg", storageDir)
    }

    private fun uriToFile(context: Context, uri: Uri, fileName: String): File? {
        context.contentResolver.openInputStream(uri)?.let { inputStream ->

            val tempFile: File = createImageFile(fileName)
            val fileOutputStream = FileOutputStream(tempFile)

            inputStream.copyTo(fileOutputStream)
            inputStream.close()
            fileOutputStream.close()

            return tempFile
        }

        return null
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        startActivityForResult(
            Intent.createChooser(
                intent.setType("image/*"),
                "Escolha uma foto"
            ),
            CODE_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE_IMAGE && resultCode == -1) {

            val imageUri: Uri = data!!.data!!
            imagePath = getRealPathFromUri(imageUri)

            //recuperar a imagem no stream
            val stream = view?.context?.contentResolver?.openInputStream(data!!.data!!)

            imageBitmap = BitmapFactory.decodeStream(stream)

            //Colocar imagem no ImageView
            image_discipline.setImageBitmap(imageBitmap)

        } else {
            Toast.makeText(view?.context, "Selecione uma foto", Toast.LENGTH_LONG).show()
        }
    }

    private fun getRealPathFromUri(uri: Uri): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(view?.context!!, uri, projection, null, null, null)
        val cursor = loader.loadInBackground()!!

        val column_idx: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result: String = cursor.getString(column_idx)
        cursor.close()

        return result
    }

}