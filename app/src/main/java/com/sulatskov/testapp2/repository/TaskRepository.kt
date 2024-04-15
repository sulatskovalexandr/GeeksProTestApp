package com.sulatskov.testapp2.repository

import android.content.Context
import com.sulatskov.testapp2.data_base.Task
import com.sulatskov.testapp2.data_base.TaskDataBase

class TaskRepository(context: Context) {
    private val database = TaskDataBase.create(context)
    private val dao = database.taskDao()

    suspend fun loadTasks() =
        dao.selectAll()

    suspend fun loadTask(taskId: Int) =
        dao.selectById(id = taskId)

    suspend fun makeTaskDone(taskId: Int) {
        dao.makeDone(id = taskId)
    }

    suspend fun deleteTask(taskId: Int) {
        dao.delete(id = taskId)
    }

    suspend fun createTask(desc: String) {
        dao.insertTask(Task(desc, false))
    }

    suspend fun updateTask(taskId: Int, desc: String) {
        dao.updateTaskDescription(taskId, desc)
    }

}