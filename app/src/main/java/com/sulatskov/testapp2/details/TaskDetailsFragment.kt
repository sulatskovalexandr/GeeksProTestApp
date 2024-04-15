package com.sulatskov.testapp2.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.sulatskov.testapp2.R
import com.sulatskov.testapp2.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskDetailsFragment : Fragment() {

    private val repository by lazy {
        TaskRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_tast_details, container, false)
        val taskDetailContainer: View = inflate.findViewById(R.id.task_details_container)
        val taskEditContainer: View = inflate.findViewById(R.id.task_details_edit_container)
        val taskDescription: TextView = inflate.findViewById(R.id.task_details_description)
        val taskDescriptionEt: EditText = inflate.findViewById(R.id.task_details_edit_description)
        val editTaskButton: MaterialButton = inflate.findViewById(R.id.task_details_edit)
        val doneTaskButton: MaterialButton = inflate.findViewById(R.id.task_details_done)
        val deleteTaskButton: MaterialButton = inflate.findViewById(R.id.task_details_delete)
        val saveTaskButton: MaterialButton = inflate.findViewById(R.id.task_details_save)

        val taskId = arguments?.getInt(TASK_ID_ARG)
        if (taskId != null) {
            lifecycleScope.launch {
                val task = repository.loadTask(taskId)
                taskDescription.text = task?.description
                taskDescriptionEt.setText(task?.description)
            }
            taskDetailContainer.isVisible = true
            taskEditContainer.isVisible = false
        } else {
            taskEditContainer.isVisible = true
            taskDetailContainer.isVisible = false
        }

        editTaskButton.setOnClickListener {
            taskDetailContainer.isVisible = false
            taskEditContainer.isVisible = true
        }
        doneTaskButton.setOnClickListener {
            lifecycleScope.launch {
                repository.makeTaskDone(taskId ?: return@launch)
                activity?.onBackPressed()
            }
        }
        deleteTaskButton.setOnClickListener {
            lifecycleScope.launch {
                repository.deleteTask(taskId ?: return@launch)
                activity?.onBackPressed()
            }
        }
        saveTaskButton.setOnClickListener {
            lifecycleScope.launch {
                if (taskId != null) {
                    repository.updateTask(taskId, taskDescriptionEt.text.toString())
                } else {
                    repository.createTask(taskDescriptionEt.text.toString())
                }
                activity?.onBackPressed()
            }
        }
        return inflate
    }

    companion object {
        const val TASK_ID_ARG = "TASK_ID_ARG"

        @JvmStatic
        fun createArgs(taskId: Int) = Bundle().apply {
            putInt(TASK_ID_ARG, taskId)
        }
    }
}