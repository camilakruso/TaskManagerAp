package com.example.taskmanagerapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

enum class Status {
    PENDENTE,
    CONCLUIDA,
    CANCELADA
}

data class Task(
    val id: Long,
    val nome: String,
    val status: Status
)

class TaskDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableQuery())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    fun insertTask(task: Task): Long {
        val values = createContentValues(task)
        return writableDatabase.insert(TABLE_TASKS, null, values)
    }

    fun taskExists(id: Long): Boolean {
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())

        return readableDatabase.query(
            TABLE_TASKS,
            arrayOf(COLUMN_ID),
            selection,
            selectionArgs,
            null,
            null,
            null
        ).use { cursor ->
            cursor.moveToFirst()
        }
    }


    fun deleteTask(id: Long): Int {
        return writableDatabase.delete(TABLE_TASKS, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val query = "SELECT * FROM $TABLE_TASKS"
        readableDatabase.rawQuery(query, null).use { cursor ->
            while (cursor.moveToNext()) {
                tasks.add(createTaskFromCursor(cursor))
            }
        }
        return tasks
    }

    fun getTaskById(id: Long): Task? {
        val query = "SELECT * FROM $TABLE_TASKS WHERE $COLUMN_ID = ?"
        return readableDatabase.rawQuery(query, arrayOf(id.toString())).use { cursor ->
            if (cursor.moveToFirst()) createTaskFromCursor(cursor) else null
        }
    }

    fun updateTask(task: Task): Int {
        val values = createContentValues(task)
        return writableDatabase.update(TABLE_TASKS, values, "$COLUMN_ID = ?", arrayOf(task.id.toString()))
    }

    private fun createTaskFromCursor(cursor: Cursor): Task {
        return Task(
            id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
            nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)),
            status = Status.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)))
        )
    }

    private fun createContentValues(task: Task): ContentValues {
        return ContentValues().apply {
            put(COLUMN_ID, task.id)
            put(COLUMN_NOME, task.nome)
            put(COLUMN_STATUS, task.status.name)
        }
    }

    private fun createTableQuery(): String {
        return """
            CREATE TABLE $TABLE_TASKS (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_NOME TEXT NOT NULL,
                $COLUMN_STATUS TEXT NOT NULL
            )
        """.trimIndent()
    }

    companion object {
        private const val DATABASE_NAME = "tasks.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TASKS = "tasks"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nome"
        private const val COLUMN_STATUS = "status"
    }
}
