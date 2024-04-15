package com.sulatskov.testapp2.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sulatskov.testapp2.R
import com.sulatskov.testapp2.data_base.Task

class TaskHolder(
    private val onClick: (Int) -> Unit,
    view: View
) : RecyclerView.ViewHolder(view) {
    private val iconStatus: ImageView = view.findViewById(R.id.item_status)
    private val taskDescription: TextView = view.findViewById(R.id.item_task_description)
    private var task: Task? = null

    init {
        view.setOnClickListener {
            task?.let { task ->
                onClick(task.id)
            }
        }

    }

    fun bind(task: Task) {
        this.task = task
        taskDescription.text = task.description
        if (task.isDone)
            iconStatus.setImageResource(R.drawable.ic_done)
        else
            iconStatus.setImageDrawable(null)
    }

}
