package com.example.shoppingmanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.shoppingmanagementapp.data.UserRepository
import com.example.shoppingmanagementapp.ui.theme.ShoppingManagementAppTheme
import com.example.shoppingmanagementapp.ui.screens.LoginScreen
import com.example.shoppingmanagementapp.ui.screens.MainScreen
import com.example.shoppingmanagementapp.ui.screens.RegisterScreen

class MainActivity : ComponentActivity() {
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userRepository = UserRepository(this)

        setContent {
            ShoppingManagementAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent(userRepository)
                }
            }
        }
    }
}

@Composable
fun AppContent(userRepository: UserRepository) {
    var currentScreen by remember { mutableStateOf("login") }
    var currentUser by remember { mutableStateOf("") }
    var shoppingList by remember { mutableStateOf(mutableMapOf<String, Int>()) }

    when (currentScreen) {
        "login" -> LoginScreen(
            onLoginSuccess = { username ->
                currentUser = username
                currentScreen = "main"
            },
            onNavigateToRegister = { currentScreen = "register" },
            userRepository = userRepository
        )
        "register" -> RegisterScreen(
            onRegister = { username, password ->
                userRepository.saveUser(username, password)
                userRepository.debugPrintUsers() // Added for debugging
                currentScreen = "login"
            },
            onNavigateBack = { currentScreen = "login" }
        )
        "main" -> MainScreen(
            currentUser = currentUser,
            shoppingList = shoppingList,
            onAddProduct = { product, quantity ->
                shoppingList = shoppingList.toMutableMap().apply {
                    put(product, getOrDefault(product, 0) + quantity)
                }
            },
            onRemoveProduct = { product ->
                shoppingList = shoppingList.toMutableMap().apply { remove(product) }
            },
            onLogout = {
                currentUser = ""
                shoppingList.clear()
                currentScreen = "login"
            }
        )
    }
}