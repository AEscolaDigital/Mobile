package com.example.school.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.school.R
import com.example.school.models.Discipline

class DashboardAdapter(var context: Context) :
    RecyclerView.Adapter<DashboardAdapter.DashViewHolder>() {


    private var listaDisciplina = emptyList<Discipline>()

    fun updateListasDisciplina(lista: List<Discipline>) {

        listaDisciplina = lista

        //Notifica que teve aterações e modifica a tela
        notifyDataSetChanged()
    }

    //Criando a viewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_teams, parent, false)

        return DashViewHolder(view)
    }

    //Constroi a holder na tela e manda a posição que for criar
    override fun onBindViewHolder(holder: DashViewHolder, position: Int) {

        val discipline = listaDisciplina[position]

        holder.tvMatter.text = discipline.name
        holder.tvNameTeacher.text = discipline.teacher_name
        Glide.with(context).load(discipline.image).into(holder.imageDiscipline)
        
    }

    //Pega a contagem de itens da tela
    override fun getItemCount(): Int {
        return listaDisciplina.size

    }

    //Cria a Holder com os elementos passados
    class DashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageDiscipline = itemView.findViewById<ImageView>(R.id.image_discipline)
        val tvMatter = itemView.findViewById<TextView>(R.id.name_matter)
        val tvNameTeacher = itemView.findViewById<TextView>(R.id.name_teacher)

    }

}