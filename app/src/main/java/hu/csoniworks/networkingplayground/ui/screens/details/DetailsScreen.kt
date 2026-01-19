package hu.csoniworks.networkingplayground.ui.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.csoniworks.networkingplayground.R
import hu.csoniworks.networkingplayground.ui.components.LoadingIndicator
import hu.csoniworks.networkingplayground.ui.components.ScreenScaffold
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme

@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel,
    onBackClicked: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    uiState.value?.let { state ->
        DetailsScreenContent(
            state = state,
            onBackClicked = onBackClicked
        )
    }
}

@Composable
private fun DetailsScreenContent(
    state: DetailsScreenUiState,
    onBackClicked: () -> Unit
) {
    ScreenScaffold(
        title = stringResource(R.string.details_screen_title),
        onBackClicked = onBackClicked
    ) {
        when (state) {
            is DetailsScreenUiState.Loading -> LoadingIndicator()
            is DetailsScreenUiState.Default -> {
                Text(
                    text = "List content for id ${state.id}"
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenContentPreview() {
    NetworkingPlaygroundTheme {
        DetailsScreenContent(
            state = DetailsScreenUiState.Default(
                id = "item_id_1234",
            ),
            onBackClicked = {}
        )
    }
}