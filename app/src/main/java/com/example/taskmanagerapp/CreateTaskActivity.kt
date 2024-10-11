package com.example.taskmanagerapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var dbHelper: TaskDatabaseHelper
    private lateinit var etTaskName: EditText
    private lateinit var etTaskId: EditText
    private lateinit var spinnerStatus: Spinner
    private lateinit var btnCreate: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        etTaskName = findViewById(R.id.etTaskName)
        etTaskId = findViewById(R.id.etTaskId)
        spinnerStatus = findViewById(R.id.spinnerStatus)
        btnCreate = findViewById(R.id.btnCreate)
        btnBack = findViewById(R.id.btnBack)

        dbHelper = TaskDatabaseHelper(this)

        val statuses = Status.values().map { it.name }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.adapter = adapter

        btnCreate.setOnClickListener {
            val nome = etTaskName.text.toString()
            val id = etTaskId.text.toString().toLongOrNull() ?: -1L
            val status = Status.valueOf(spinnerStatus.selectedItem.toString())

            if (nome.isEmpty()) {
                Toast.makeText(this, "Nome da tarefa não pode ser vazio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (id == -1L) {
                Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dbHelper.taskExists(id)) {
                Toast.makeText(this, "ID já existente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val insertedId = dbHelper.insertTask(Task(id = id, nome = nome, status = status))
            if (insertedId != -1L) {
                Toast.makeText(this, "Tarefa Criada com ID: $insertedId", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao criar a tarefa", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
