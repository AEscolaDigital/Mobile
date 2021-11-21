package com.example.school.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.school.R

class TaskFragment : Fragment() {

    lateinit var btn_add_task: Button


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