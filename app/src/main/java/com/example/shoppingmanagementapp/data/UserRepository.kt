package com.example.shoppingmanagementapp.data

import android.content.Context
import android.util.Log
import java.io.File

class UserRepository(private val context: Context) {
    private val fileName = "users.txt"
    private val file: File = File(context.getExternalFilesDir(null), fileName)

    init {
        if (!file.exists()) {
            file.createNewFile()
            Log.d("UserRepository", "Created new file at ${file.absolutePath}")
        } else {
            Log.d("UserRepository", "Using existing file at ${file.absolutePath}")
        }
    }

    fun saveUser(username: String, password: String) {
        try {
            file.appendText("$username:$password\n")
            Log.d("UserRepository", "Saved user: $username")
        } catch (e: Exception) {
            Log.e("UserRepository", "Error saving user", e)
        }
    }

    fun getUsers(): Map<String, String> {
        val users = mutableMapOf<String, String>()
        try {
            file.readLines().forEach { line ->
                val parts = line.split(":")
                if (parts.size == 2) {
                    users[parts[0]] = parts[1]
                }
            }
            Log.d("UserRepository", "Retrieved ${users.size} users")
        } catch (e: Exception) {
            Log.e("UserRepository", "Error reading users", e)
        }
        return users
    }

    fun isValidUser(username: String, password: String): Boolean {
        val users = getUsers()
        return users[username] == password
    }

    fun debugPrintUsers() {
        Log.d("UserRepository", "All users: ${getUsers()}")
    }
}