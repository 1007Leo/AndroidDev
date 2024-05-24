package com.mmcs.todolist.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mmcs.todolist.R
import com.mmcs.todolist.database.GlobalDBApplication
import com.mmcs.todolist.database.ViewModelFactory
import com.mmcs.todolist.viewmodel.ToDoListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var dbApp: GlobalDBApplication
    private val sharedData: ToDoListViewModel by viewModels {
        ViewModelFactory(dbApp.getDB().dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        dbApp = applicationContext as GlobalDBApplication

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}