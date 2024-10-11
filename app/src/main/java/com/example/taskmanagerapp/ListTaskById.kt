package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListTaskByIdActivity : AppCompatActivity() {
    private lateinit var dbHelper: TaskDatabaseHelper
    private lateinit var etTaskId: EditText
    private lateinit var btnList: Button
    private lateinit var btnBack: Button
    private lateinit var tvTaskDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_task_by_id)

        etTaskId = findViewById(R.id.etTaskId)
        btnList = findViewById(R.id.btnList)
        btnBack = findViewById(R.id.btnBack)
        tvTaskDetails = findViewById(R.id.tvTaskDetails)

        dbHelper = TaskDatabaseHelper(this)

        btnList.setOnClickListener {
            val id = etTaskId.text.toString().toLongOrNull() ?: -1L

            if (id == -1L) {
                Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val task = dbHelper.getTaskById(id)
            if (task != null) {
                val taskDetails = "ID: ${task.id}\nNome: ${task.nome}\nStatus: ${task.status}"
                tvTaskDetails.text = taskDetails
            } else {
                Toast.makeText(this, "Tarefa não encontrada", Toast.LENGTH_SHORT).show()
                tvTaskDetails.text = ""
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
