package com.example.school.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.school.R
import com.example.school.models.Task
import java.util.*

class TaskFragment : Fragment() {

    lateinit var btn_add_task: Button
    lateinit var edNome: EditText
    lateinit var edDescription: EditText
    lateinit var edData: EditText
    lateinit var edPontuacao : EditText
    lateinit var edAnexo : EditText


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

        edNome = view.findViewById(R.id.ed_nome_task)
        edDescription = view.findViewById(R.id.ed_descricao_task)
        edData = view.findViewById(R.id.ed_data)
        edPontuacao = view.findViewById(R.id.ed_pontuacao)
        edAnexo = view.findViewById(R.id.ed_anexo)


        val task:Task = Task()
        task.id = 1
        task.description = "I do not  know"
        task.attachment = "Vai se ..."
        task.deliveryDate = Date()
        task.title = "Consumir API "
        task.punctuation = 20


        btn_add_task.setOnClickListener{
            val view = View.inflate(context, R.layout.dialog_task, null)

            val builder = AlertDialog.Builder(context)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
}