package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListTasksActivity : AppCompatActivity() {
    private lateinit var dbHelper: TaskDatabaseHelper
    private lateinit var listView: ListView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_tasks)

        dbHelper = TaskDatabaseHelper(this)
        listView = findViewById(R.id.listView)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        loadTasks()
    }

    private fun loadTasks() {
        val tasks = dbHelper.getAllTasks()
        val taskNames = tasks.map { "${it.id}: ${it.nome} - ${it.status}" }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskNames)
        listView.adapter = adapter

        if (taskNames.isEmpty()) {
            Toast.makeText(this, "Nenhuma tarefa encontrada.", Toast.LENGTH_SHORT).show()
        }
    }
}
