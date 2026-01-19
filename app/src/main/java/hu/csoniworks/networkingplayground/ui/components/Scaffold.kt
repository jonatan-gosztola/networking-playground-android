package hu.csoniworks.networkingplayground.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.csoniworks.networkingplayground.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold(
    title: String,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back_24),
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            )
        },
    ) { contentPadding ->
        Column(
            modifier = modifier
                .padding(12.dp)
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun ScreenScaffoldPreview() {
    ScreenScaffold(
        title = "Screen title",
        onBackClicked = {},
    ) {
        Text("Scaffold content")
    }
}