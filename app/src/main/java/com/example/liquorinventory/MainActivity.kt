package com.example.liquorinventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.liquorinventory.ui.theme.LiquorInventoryTheme

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    private val db = FirebaseFirestore.getInstance() // a database connection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiquorInventoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        // test connection
        addTestLiquor()
    }

    private fun addTestLiquor() {
        val liquorItem = hashMapOf(
            "name" to "Whiskey", "type" to "Bourgon", "quantity" to 10, "price" to 49.99
        )

        db.collection("liquorItems").add(liquorItem).addOnSuccessListener { documentReference ->
            println("Item added with ID: ${documentReference.id}")
        }.addOnFailureListener { e ->
            println("Error adding item: $e")
        }

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiquorInventoryTheme {
        Greeting("Android")
    }
}