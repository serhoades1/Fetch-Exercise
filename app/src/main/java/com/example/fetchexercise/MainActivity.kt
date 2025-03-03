package com.example.fetchexercise

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
import com.example.fetchexercise.ui.theme.FetchExerciseTheme
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchexercise.JsonUtils

class MainActivity : AppCompatActivity() {
    private val viewModel: ItemViewModel by viewModels()
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = JsonUtils.readJsonFromAssets(this, "hiring.json")
        println("Loaded JSON: $jsonString")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        itemAdapter = ItemAdapter(emptyList())
        recyclerView.adapter = itemAdapter

        viewModel.items.observe(this) { items ->
            itemAdapter.updateItems(items)
        }

        viewModel.fetchItems()
    }
}