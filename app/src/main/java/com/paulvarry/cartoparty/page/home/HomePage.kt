package com.paulvarry.cartoparty.page.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.paulvarry.cartoparty.R
import com.paulvarry.cartoparty.page.main.MainActivity

@Composable
fun HomePage(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 4.dp,
            )
        },
    ) {
        HomePageContent(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomePageContent(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val content by viewModel.slugs.observeAsState()

    LazyColumn {
        item {
            var value by remember { mutableStateOf("") }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = value,
                onValueChange = { value = it },
                label = { Text("Create new slug") },
                maxLines = 1,
            )
            Button(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = { navController.navigate(MainActivity.Route.Map.path + "/${value.trim()}") },
                enabled = value.isNotBlank()
            ) {
                Text("Open map")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
        }
        items(content?.size ?: 0) {
            val item = content?.get(it) ?: return@items

            ListItem(
                text = { Text(item.slug) },
                secondaryText = { Text(item.createdAt.toString()) },
                modifier = Modifier.clickable {
                    navController.navigate(MainActivity.Route.Map.path + "/${item.slug}")
                },
            )
        }
    }
}

