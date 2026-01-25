package hu.csoniworks.networkingplayground.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.csoniworks.networkingplayground.R
import hu.csoniworks.networkingplayground.data.models.Post
import hu.csoniworks.networkingplayground.ui.components.ErrorBox
import hu.csoniworks.networkingplayground.ui.components.LoadingIndicator
import hu.csoniworks.networkingplayground.ui.components.PostCard
import hu.csoniworks.networkingplayground.ui.components.RandomImage
import hu.csoniworks.networkingplayground.ui.components.ScreenScaffold
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ListScreen(
    viewModel: ListScreenViewModel,
    onBackClicked: () -> Unit,
    onItemClicked: (id: Int) -> Unit,
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
    onItemClicked: (id: Int) -> Unit,
    onBackClicked: () -> Unit,
) {
    ScreenScaffold(
        title = stringResource(R.string.list_screen_title),
        onBackClicked = onBackClicked
    ) {
        when (state) {
            is ListScreenUiState.Loading -> LoadingIndicator()

            is ListScreenUiState.Error -> ErrorBox()

            is ListScreenUiState.Default -> DefaultContent(
                state = state,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
private fun DefaultContent(
    state: ListScreenUiState.Default,
    onItemClicked: (id: Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = rememberLazyListState()
    ) {
        item {
            RandomImage()
        }

        items(state.list) {
            PostCard(
                onItemClicked = onItemClicked,
                post = it,
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
                list = persistentListOf()
            ),
            onItemClicked = {},
            onBackClicked = {},
        )
    }
}
