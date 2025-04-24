package com.example.roomcomplete

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight


@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.observeAsState(emptyList())
    var input by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Note") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (input.isNotBlank() && input2.isNotBlank()) {
                viewModel.addNote(input, input2)
                input = ""
                input2 = ""
            }
        }) {
            Text("Add")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(notes) { note ->
                Column(
                    modifier = Modifier
                        .background(Color(0xFFF0F0F0))
                        .padding(8.dp)


                ) {
                    Text(
                        text = note.text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.deleteNote(note) }
                            .padding(8.dp),
                        style = androidx.compose.ui.text.TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = note.text2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.deleteNote(note) }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}
