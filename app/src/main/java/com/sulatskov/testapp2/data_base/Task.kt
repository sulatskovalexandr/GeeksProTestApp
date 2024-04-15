package com.sulatskov.testapp2.data_base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Task.TABLE_NAME)
data class Task(
    val description: String,
    val isDone: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
) {

    companion object {
        const val TABLE_NAME = "TASKS"
    }
}
