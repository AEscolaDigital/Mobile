package com.example.school.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.api.discipline.Discipline

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
    ): DashboardAdapter.DashViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_teams, parent, false)

        return DashViewHolder(view)
    }

    //Constroi a holder na tela e manda a posição que for criar

    override fun onBindViewHolder(holder: DashViewHolder, position: Int) {

        val discipline = listaDisciplina[position]

        holder.tvMatter.text = String()
        holder.tvNameTeacher.text = String()

    }

    //Pega a contagem de itens da tela

    override fun getItemCount(): Int {
        return listaDisciplina.size

    }

    //Cria a Holder com os elementos passados
    class DashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //    val imageDiscipline = itemView.findViewById<ImageView>(R.id.imageDiscipline)
        val tvMatter = itemView.findViewById<TextView>(R.id.name_matter)
        val tvNameTeacher = itemView.findViewById<TextView>(R.id.name_teacher)

    }
}