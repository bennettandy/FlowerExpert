package uk.co.avsoftware.flowerexpert.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import uk.co.avsoftware.flowerexpert.ui.nav.MainNavHost

@Composable
fun MainScaffold(navHostController: NavHostController) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { MainTopBar() },
        bottomBar = { MainBottomBar(navHostController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        MainNavHost(
            navHostController = navHostController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}