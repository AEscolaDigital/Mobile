package com.example.school.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.adapter.DashboardAdapter
import com.example.school.api.school.ApiSchool
import com.example.school.models.Discipline
import com.example.school.models.Login
import com.example.school.models.UserLogiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsFragment : Fragment() {
    private lateinit var recyclerViewTurmas :RecyclerView
    private lateinit var dashBoardAdapter: DashboardAdapter

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

        recyclerViewTurmas = view.findViewById(R.id.recycler_teams)!!
        dashBoardAdapter = DashboardAdapter(context)

        //layout of recyclerview, grid two columns
        recyclerViewTurmas.layoutManager = GridLayoutManager(context, 2)

        //* Definindo a Adapter da RV(RecycleView)
        recyclerViewTurmas.adapter = dashBoardAdapter

        //call the shared preferences school and get jwt
        val sharedPreferences = context.getSharedPreferences("school", 0)
        val jwt = sharedPreferences.getString("JWT", "teste de chamada saida vazia")

//z3h9d1ma

        //call the api for populate disciplines of studant
        val remote = ApiSchool.SchoolEndPoint().dashboardService()
        val call: Call<List<Discipline>> = remote.listDisciplines("Bearer $jwt")

        //aply a request async and get the response
        call.enqueue(object : Callback<List<Discipline>> {
            override fun onResponse(call: Call<List<Discipline>>, response: Response<List<Discipline>>) {
                Log.i("RESPONSE", response.body().toString())
                Log.i("RESPONSE", response.message().toString())
                Log.i("RESPONSE", response.code().toString())
                Log.i("RESPONSE", response.errorBody().toString())
                Log.i("RESPONSE", response.isSuccessful.toString())
                Log.i("RESPONSE", response.headers().toString())
                Log.i("RESPONSE", response.raw().toString())
            }

            override fun onFailure(call: Call<List<Discipline>>, t: Throwable) {
                Log.i("REQUEST", "FAIL")
            }
        })
/*
        call.enqueue(object : Callback<List<Discipline>> {
            override fun onResponse(call: Call<List<Discipline>>, response: Response<List<Discipline>>) {
                /*dashBoardAdapter.updateListasDisciplina(response.body()!!)*/
                Log.i("RESPONSE", response.body().toString())
                Log.i("RESPONSE", response.message().toString())
                Log.i("RESPONSE", response.code().toString())
                Log.i("RESPONSE", response.errorBody().toString())
                Log.i("RESPONSE", response.isSuccessful.toString())
                Log.i("RESPONSE", response.headers().toString())
                Log.i("RESPONSE", response.raw().toString())

            }

            override fun onFailure(call: Call<List<Discipline>>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }

        })
*/

    }

}