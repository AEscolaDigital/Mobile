package com.example.school.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.adapter.TaskAdapter
import com.example.school.api.school.ApiSchool
import com.example.school.models.Discipline
import com.example.school.models.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TaskFragment : Fragment() {
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerViewTask: RecyclerView

    lateinit var btn_add_task: Button

    lateinit var btn_finalizar: Button
    lateinit var name_task: EditText
    lateinit var description_task: EditText
    lateinit var date_task: EditText
    lateinit var pontuacao: EditText
    lateinit var references: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_teams)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView: View = inflater.inflate(R.layout.dialog_task, container, false)
        //return rootView
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val context = view.context

        btn_add_task = view.findViewById(R.id.btn_add_task)

        recyclerViewTask = view.findViewById(R.id.recycler_task)
        taskAdapter = TaskAdapter(context)

        //layout da recyclerView
        recyclerViewTask.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        //Definindo o adapter da RecyclerView
        recyclerViewTask.adapter = taskAdapter

        //call the shared preferences school and get jwt
        val sharedPreferences = context.getSharedPreferences("school", 0)
        val jwt = sharedPreferences.getString("JWT", "teste de chamada saida vazia")

        //call the api for populate task of studant
        val remote = ApiSchool.SchoolEndPoint().taskService()
        val call: Call<List<Task>> = remote.listTask("Bearer $jwt")

        //aply a request async and get the response

        call.enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {

                if (response.code() == 200) {
                    Log.i("XPTO *******", response.body().toString())
                    //taskAdapter.updateListaTask(response.body()!!)
                }
//                Log.i("RESPONSE body", response.body().toString())
//                Log.i("RESPONSE", response.message().toString())
//                Log.i("RESPONSE", response.code().toString())
//                Log.i("RESPONSE", response.errorBody().toString())
//                Log.i("RESPONSE", response.isSuccessful.toString())
//                Log.i("RESPONSE", response.headers().toString())
//                Log.i("RESPONSE", response.raw().toString())

            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                /*Log.i("REQUEST", "FAIL")*/
            }
        })

        btn_add_task.setOnClickListener {

            val view = View.inflate(context, R.layout.dialog_task, null)

            val builder = AlertDialog.Builder(context)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            //Inputs e buttons do dialog de task - instâncias
            btn_finalizar = view.findViewById(R.id.btn_finalizar)

            name_task = view.findViewById(R.id.ed_nome_task)
            description_task = view.findViewById(R.id.ed_descricao_task)
            date_task = view.findViewById(R.id.ed_data)
            pontuacao = view.findViewById(R.id.ed_pontuacao)
            references = view.findViewById(R.id.ed_anexo)

            btn_finalizar.setOnClickListener {
                Log.i("XPTO", "Botão finalizar tarefas")
            }
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        }
    }

}


