package com.sulatskov.testapp2.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sulatskov.testapp2.R
import com.sulatskov.testapp2.details.TaskDetailsFragment
import com.sulatskov.testapp2.repository.TaskRepository
import kotlinx.coroutines.launch

class TasksFragment : Fragment() {

    private val adapter = TasksAdapter(
        onTaskClick = { taskId ->
            findNavController().navigate(
                resId = R.id.action_tasksFragment_to_taskDetailsFragment,
                args = TaskDetailsFragment.createArgs(taskId),
            )
        }
    )
    private val repository: TaskRepository by lazy {
        TaskRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)
        val recycler: RecyclerView = view.findViewById(R.id.tasks_main_list)
        val addButton: FloatingActionButton = view.findViewById(R.id.add_button)
        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.adapter = adapter
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_taskDetailsFragment)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            try {
                val tasks = repository.loadTasks()
                adapter.setTasks(tasks)
            } catch (t: Throwable) {
                context?.let { context ->
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}