package hu.csoniworks.networkingplayground.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.csoniworks.networkingplayground.data.models.Post
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme

@Composable
fun PostCard(
    onItemClicked: (id: Int) -> Unit,
    post: Post,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clickable(
                onClick = { onItemClicked(post.id) }
            )
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
private fun PostCardPreview() {
    NetworkingPlaygroundTheme {
        PostCard(
            post = Post(
                userId = 1,
                id = 2,
                title = "Nem fogja elhinni mi történt",
                body = "Az történt, hogy tulajdonképen, amikor megjelent a helyszínen az alany, akkor hirtelen átment a helyszínen és nem mondott semmit, és senki más sem volt ott ebben az időpontban.",
            ),
            onItemClicked = {}
        )
    }
}