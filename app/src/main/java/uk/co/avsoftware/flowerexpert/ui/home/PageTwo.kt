package uk.co.avsoftware.flowerexpert.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PageTwo(navHostController: NavHostController, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text =
            """
                    PAGE TWO
                    
                    XX
                    
                """.trimIndent(),
        )
        Button(onClick = { navHostController.popBackStack() }){
            Text("Back")
        }
    }
}