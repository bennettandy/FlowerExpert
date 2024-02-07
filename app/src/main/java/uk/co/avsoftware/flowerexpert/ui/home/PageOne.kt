package uk.co.avsoftware.flowerexpert.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uk.co.avsoftware.flowerexpert.ui.nav.Screen

@Composable
fun PageOne(navHostController: NavHostController, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = modifier,
            text =
            """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button X times.
                """.trimIndent(),
        )
        Button(onClick = { navHostController.navigate(Screen.ScreenTwo.route) }){
            Text("Next")
        }
    }
}