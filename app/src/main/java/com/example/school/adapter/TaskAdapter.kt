package com.example.school.adapter

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.school.R
import com.example.school.activities.MainActivity
import com.example.school.activities.ViewTaskActivity
import com.example.school.api.school.ApiSchool
import com.example.school.models.Task
import com.example.school.models.TaskList

class TaskAdapter(var context: Context) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var listaTask = emptyList<TaskList>()

    fun updateListaTask(lista: List<TaskList>) {

        listaTask = lista

        //Notifica que teve aterações e modifica a tela
        notifyDataSetChanged()


    }

    //Criando a viewHolder
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_task, parent, false)

        return TaskViewHolder(view)


    }


    //Constroi a holder na tela e manda a posição que for criar
    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val task = listaTask[position]

        holder.tv_name_task.text = task.name
        holder.tv_name_task.setOnClickListener {

            //start a activity viewTaskActivity
            val intent = Intent(context, ViewTaskActivity::class.java)
            context.startActivity(intent)

            //start activity with a fragment viewTaskActivity
            
        }
/*        holder.ed_description.text = task.date_delivery
        holder.ed_deliveryDate.text = task.deliveryDate.toString()
        holder.ed_punctuation.text = task.punctuation.toString()
        holder.ed_attachment.text = task.attachment*/


    }

    //Pega a contagem de itens da tela
    override fun getItemCount(): Int {
        return listaTask.size
    }


    //Cria a Holder com os elementos passados
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv_name_task = itemView.findViewById<TextView>(R.id.tv_name_task)
 /*       val ed_description = itemView.findViewById<TextView>(R.id.ed_descricao_task)
        val ed_deliveryDate = itemView.findViewById<TextView>(R.id.ed_data)
        val ed_punctuation = itemView.findViewById<TextView>(R.id.ed_pontuacao)
        val ed_attachment = itemView.findViewById<TextView>(R.id.ed_anexo)*/

    }

}
