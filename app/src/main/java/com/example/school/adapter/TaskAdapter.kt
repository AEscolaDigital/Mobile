package com.example.school.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.api.school.ApiSchool
import com.example.school.models.Task

class TaskAdapter(var context: Context) : RecyclerView.Adapter<TaskAdapter.DashViewHolder>(){

    private var listaTask = emptyList<ApiSchool.Task>()

    fun updateListaTask(lista: List<ApiSchool.Task>){

        listaTask = lista

        //Notifica que teve aterações e modifica a tela
        notifyDataSetChanged()
    }

    //Criando a viewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskAdapter.DashViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_task, parent, false)

        return DashViewHolder(view)
    }

    //Constroi a holder na tela e manda a posição que for criar
    override fun onBindViewHolder(holder: TaskAdapter.DashViewHolder, position: Int) {
        val task = listaTask[position]

        holder.tv_name_task.text = task
    }

    override fun getItemCount(): Int {
    }




    //Cria a Holder com os elementos passados
    class DashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageTask = itemView.findViewById<ImageView>(R.id.image_task)
        val tv_name_task = itemView.findViewById<TextView>(R.id.tv_name_task)
        val btn_cancel = itemView.findViewById<TextView>(R.id.btn_cancel_task)

    }




}
