package com.example.school.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.adapter.DashboardAdapter

class TeamsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
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

        // Determinar o layout da RV(RecycleView)
        /*recyclerViewTurmas.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)*/

        recyclerViewTurmas.layoutManager = GridLayoutManager(context, 2)

        //* Definindo a Adapter da RV(RecycleView)
        recyclerViewTurmas.adapter = dashBoardAdapter
    }


}