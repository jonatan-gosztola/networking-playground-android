package hu.csoniworks.networkingplayground.ui.screens.list

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hu.csoniworks.networkingplayground.R
import hu.csoniworks.networkingplayground.ui.components.ScreenScaffold
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme

@Composable
fun ListScreen(
    onBackClicked: () -> Unit,
    onItemClicked: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    ListScreenContent(
        onItemClicked = onItemClicked,
        onBackClicked = onBackClicked,
    )
}

@Composable
private fun ListScreenContent(
    onItemClicked: (id: String) -> Unit,
    onBackClicked: () -> Unit,
) {
    ScreenScaffold(
        title = stringResource(R.string.list_screen_title),
        onBackClicked = onBackClicked
    ) {
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
            onItemClicked = {},
            onBackClicked = {},
        )
    }
}
