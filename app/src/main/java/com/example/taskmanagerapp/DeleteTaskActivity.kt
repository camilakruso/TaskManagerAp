package com.example.taskmanagerapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteTaskActivity : AppCompatActivity() {
    private lateinit var dbHelper: TaskDatabaseHelper
    private lateinit var etTaskId: EditText
    private lateinit var btnDelete: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_task)

        etTaskId = findViewById(R.id.etTaskId)
        btnDelete = findViewById(R.id.btnDelete)
        btnBack = findViewById(R.id.btnBack)

        dbHelper = TaskDatabaseHelper(this)

        btnDelete.setOnClickListener {
            val id = etTaskId.text.toString().toLongOrNull() ?: -1L

            if (id == -1L) {
                Toast.makeText(this, "ID inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!dbHelper.taskExists(id)) {
                Toast.makeText(this, "ID não encontrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rowsDeleted = dbHelper.deleteTask(id)
            if (rowsDeleted > 0) {
                Toast.makeText(this, "Tarefa com ID: $id deletada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao deletar a tarefa", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
