package com.sulatskov.testapp2

import android.app.Application
import com.sulatskov.testapp2.data_base.TaskDataBase

class TaskApp : Application() {

    val database by lazy { TaskDataBase.create(this) }

}