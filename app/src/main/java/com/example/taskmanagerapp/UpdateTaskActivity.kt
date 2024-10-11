package com.example.taskmanagerapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateTaskActivity : AppCompatActivity() {
    private lateinit var dbHelper: TaskDatabaseHelper
    private lateinit var etTaskName: EditText
    private lateinit var etTaskId: EditText
    private lateinit var spinnerStatus: Spinner
    private lateinit var btnUpdate: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        etTaskName = findViewById(R.id.etTaskName)
        etTaskId = findViewById(R.id.etTaskId)
        spinnerStatus = findViewById(R.id.spinnerStatus)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnBack = findViewById(R.id.btnBack)

        dbHelper = TaskDatabaseHelper(this)

        val statuses = Status.values().map { it.name }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, statuses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerStatus.adapter = adapter

        btnUpdate.setOnClickListener {
            val nome = etTaskName.text.toString()
            val id = etTaskId.text.toString().toLongOrNull() ?: -1L
            val status = Status.valueOf(spinnerStatus.selectedItem.toString())

            if (nome.isEmpty()) {
                Toast.makeText(this, "Nome da tarefa não pode ser vazio", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (id == -1L || !dbHelper.taskExists(id)) {
                Toast.makeText(this, "ID inválido ou não existe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedRows = dbHelper.updateTask(Task(id = id, nome = nome, status = status))
            if (updatedRows > 0) {
                Toast.makeText(this, "Tarefa atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao atualizar a tarefa", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
