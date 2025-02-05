package com.example.kotlincoroutines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val user by viewModel.userState.collectAsState()
    val posts by viewModel.postsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserData()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        user?.let {
            Text(text = "User: ${it.name}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Email: ${it.email}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(text = "User Posts", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(posts ?: emptyList()) { post ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(
                            color = Color.LightGray,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp)
                ) {
                    Column {
                        Text(text = post.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreen() {
    UserScreen(viewModel = UserViewModel())
}
