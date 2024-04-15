package com.sulatskov.testapp2.data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM ${Task.TABLE_NAME}")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM ${Task.TABLE_NAME} WHERE id =:id")
    suspend fun selectById(id: Int): Task?

    @Query("UPDATE ${Task.TABLE_NAME} SET isDone=1 WHERE id=:id")
    suspend fun makeDone(id: Int)

    @Query("SELECT * FROM ${Task.TABLE_NAME}")
    suspend fun selectAll(): List<Task>

    @Query("DELETE FROM ${Task.TABLE_NAME} WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("UPDATE ${Task.TABLE_NAME} SET description=:description WHERE id=:id")
    suspend fun updateTaskDescription(id: Int, description: String)

}
