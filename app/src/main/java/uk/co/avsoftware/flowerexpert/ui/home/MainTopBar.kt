package uk.co.avsoftware.flowerexpert.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(uiState: State<HomeUiState>, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Column {
                Text(
                    text = "Matched ${uiState.value.classifications.size} landmarks",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                uiState.value.classifications.forEach {
                    with(it) {
                        Text(
                            text = "${name}: ${score.shortDecimal()}%, ",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    )
}

fun Float.shortDecimal(): String =
    "%.2f".format(this)