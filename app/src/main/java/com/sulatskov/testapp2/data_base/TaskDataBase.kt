package com.sulatskov.testapp2.data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        fun create(context: Context) =
            Room.databaseBuilder(context, TaskDataBase::class.java, "task_db")
                .build()
    }
}