package com.example.shoppingmanagementapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    currentUser: String,
    shoppingList: Map<String, Int>,
    onAddProduct: (String, Int) -> Unit,
    onRemoveProduct: (String) -> Unit,
    onLogout: () -> Unit
) {
    var newProduct by remember { mutableStateOf("") }
    var newQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ברוך הבא, $currentUser!", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = onLogout) {
                Text("התנתק")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newProduct,
                onValueChange = { newProduct = it },
                label = { Text("שם המוצר") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = newQuantity,
                onValueChange = { newQuantity = it },
                label = { Text("כמות") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (newProduct.isNotEmpty() && newQuantity.isNotEmpty()) {
                        onAddProduct(newProduct, newQuantity.toIntOrNull() ?: 0)
                        newProduct = ""
                        newQuantity = ""
                    }
                }
            ) {
                Text("הוסף")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(shoppingList.toList()) { (product, quantity) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("$product: $quantity", modifier = Modifier.weight(1f))
                    Button(onClick = { onRemoveProduct(product) }) {
                        Text("הסר")
                    }
                }
            }
        }
    }
}