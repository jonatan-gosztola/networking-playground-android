package hu.csoniworks.networkingplayground.ui.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hu.csoniworks.networkingplayground.R
import hu.csoniworks.networkingplayground.ui.components.ScreenScaffold
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme

@Composable
fun DetailsScreen(
    id: String,
    onBackClicked: () -> Unit,
) {
    DetailsScreenContent(
        id = id,
        onBackClicked = onBackClicked
    )
}

@Composable
private fun DetailsScreenContent(
    id: String,
    onBackClicked: () -> Unit
) {
    ScreenScaffold(
        title = stringResource(R.string.details_screen_title),
        onBackClicked = onBackClicked
    ) {
        Text(
            text = "List content for id ${id}"
        )
    }
}

@Preview
@Composable
private fun DetailsScreenContentPreview() {
    NetworkingPlaygroundTheme {
        DetailsScreenContent(
            id = "item_id_1234",
            onBackClicked = {}
        )
    }
}