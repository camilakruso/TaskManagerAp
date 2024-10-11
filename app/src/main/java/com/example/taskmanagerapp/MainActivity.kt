package com.example.taskmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnCreateTask: Button
    private lateinit var btnDeleteTask: Button
    private lateinit var btnListTasks: Button
    private lateinit var btnListTaskById: Button
    private lateinit var btnUpdateTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreateTask = findViewById(R.id.btnCreateTask)
        btnDeleteTask = findViewById(R.id.btnDeleteTask)
        btnListTasks = findViewById(R.id.btnListTasks)
        btnListTaskById = findViewById(R.id.btnListTaskById)
        btnUpdateTask = findViewById(R.id.btnUpdateTask)

        btnCreateTask.setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }

        btnDeleteTask.setOnClickListener {
            startActivity(Intent(this, DeleteTaskActivity::class.java))
        }

        btnListTasks.setOnClickListener {
            startActivity(Intent(this, ListTasksActivity::class.java))
        }

        btnListTaskById.setOnClickListener {
            startActivity(Intent(this, ListTaskByIdActivity::class.java))
        }

        btnUpdateTask.setOnClickListener {
            startActivity(Intent(this, UpdateTaskActivity::class.java))
        }
    }
}
