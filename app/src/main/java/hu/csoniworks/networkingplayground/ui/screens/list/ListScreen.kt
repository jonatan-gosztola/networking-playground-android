package hu.csoniworks.networkingplayground.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.csoniworks.networkingplayground.R
import hu.csoniworks.networkingplayground.ui.components.LoadingIndicator
import hu.csoniworks.networkingplayground.ui.components.ScreenScaffold
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme

@Composable
fun ListScreen(
    viewModel: ListScreenViewModel,
    onBackClicked: () -> Unit,
    onItemClicked: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    uiState.value?.let { state ->
        ListScreenContent(
            state = state,
            onItemClicked = onItemClicked,
            onBackClicked = onBackClicked,
        )
    }
}

@Composable
private fun ListScreenContent(
    state: ListScreenUiState,
    onItemClicked: (id: String) -> Unit,
    onBackClicked: () -> Unit,
) {
    ScreenScaffold(
        title = stringResource(R.string.list_screen_title),
        onBackClicked = onBackClicked
    ) {

        when (state) {
            is ListScreenUiState.Loading -> LoadingIndicator()

            is ListScreenUiState.Default -> DefaultContent(
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
private fun DefaultContent(
    onItemClicked: (String) -> Unit,
) {
    Column {
        Text(
            text = "List content"
        )

        Button(
            onClick = { onItemClicked("id1234") }
        ) {
            Text(
                text = "Item - click for details"
            )
        }
    }
}

@Preview
@Composable
private fun ListScreenContentPreview() {
    NetworkingPlaygroundTheme {
        ListScreenContent(
            state = ListScreenUiState.Default(
                list = emptyList()
            ),
            onItemClicked = {},
            onBackClicked = {},
        )
    }
}
