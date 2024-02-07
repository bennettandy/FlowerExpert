package uk.co.avsoftware.flowerexpert.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun MainBottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Bottom)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Photo,
                    contentDescription = "Open gallery"
                )
            }
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Take photo"
                )
            }
        }
    }
}