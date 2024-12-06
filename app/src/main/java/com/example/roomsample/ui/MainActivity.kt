package com.example.roomsample.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.roomsample.ui.theme.MyappTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen(
                        modifier = Modifier.padding(innerPadding),
                        context = LocalContext.current
                    )
                }
            }
        }
    }
}




@Composable
fun Screen(
    modifier: Modifier = Modifier,
    context: Context
) {
    val database = ItemDataBase.getDatabase(context)
    val repository = ItemRepository(database.itemDao())
    val viewModel: ItemViewModel = viewModel(factory = ItemViewModelFactory(repository))

    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    val items by viewModel.items.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("Text 1") }
        )

        OutlinedTextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text("Text 2") }
        )

        Button(
            onClick = {
                if (text1.isNotEmpty() && text2.isNotEmpty()) {
                    viewModel.saveItem(Item(text1 = text1, text2 = text2))
                    Log.e("ItemViewModel", "Item saved: $text1, $text2")
                }else{
                    Log.e("ItemViewModel", "Item not saved: $text1, $text2")
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(items) { item ->
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${item.text1} - ${item.text2}"
                    )
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.clickable {
                            viewModel.deleteItemById(item.wordId)
                        }
                    )
                }
            }
        }
    }
}

