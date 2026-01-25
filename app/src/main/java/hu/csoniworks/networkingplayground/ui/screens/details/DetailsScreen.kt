package hu.csoniworks.networkingplayground.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hu.csoniworks.networkingplayground.R
import hu.csoniworks.networkingplayground.data.models.Comment
import hu.csoniworks.networkingplayground.data.models.Post
import hu.csoniworks.networkingplayground.ui.components.ErrorBox
import hu.csoniworks.networkingplayground.ui.components.LoadingIndicator
import hu.csoniworks.networkingplayground.ui.components.PostCard
import hu.csoniworks.networkingplayground.ui.components.ScreenScaffold
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

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
            is DetailsScreenUiState.Error -> ErrorBox()
            is DetailsScreenUiState.Default -> {
                CommentsContent(
                    post = state.post,
                    comments = state.comments
                )
            }
        }
    }
}

@Composable
private fun CommentsContent(
    post: Post,
    comments: ImmutableList<Comment>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        PostCard(
            onItemClicked = {},
            post = post,
        )

        LazyColumn(
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(comments) { comment ->
                Comment(comment)
            }
        }
    }
}

@Composable
private fun Comment(
    comment: Comment
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceDim,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = comment.name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )

        Text(
            text = comment.body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun DetailsScreenContentPreview() {
    NetworkingPlaygroundTheme {
        DetailsScreenContent(
            state = DetailsScreenUiState.Default(
                id = 1234,
                post = Post(
                    userId = 1,
                    id = 2,
                    title = "Nem fogja elhinni mi történt",
                    body = "Az történt, hogy tulajdonképen, amikor megjelent a helyszínen a főszereplő, akkor hirtelen átment a helyszínen és nem mondott semmit, és senki más sem volt ott ebben az időpontban.",
                ),
                comments = persistentListOf(
                    Comment(
                        postId = 1,
                        id = 11,
                        name = "kommenthuszar4",
                        email = "koment@huszar.hu",
                        body = "iszonyatos",
                    ),
                    Comment(
                        postId = 1,
                        id = 11,
                        name = "bunkoferi",
                        email = "bunkoferi@huszar.hu",
                        body = "menj a feneeebe, tee"
                    ),
                )
            ),
            onBackClicked = {}
        )
    }
}