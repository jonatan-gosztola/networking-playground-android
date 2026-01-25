package hu.csoniworks.networkingplayground.ui.screens.details

import hu.csoniworks.networkingplayground.data.models.Comment
import hu.csoniworks.networkingplayground.data.models.Post
import kotlinx.collections.immutable.ImmutableList

sealed class DetailsScreenUiState {
    data object Loading : DetailsScreenUiState()

    data object Error : DetailsScreenUiState()

    data class Default(
        val id: Int,
        val post: Post,
        val comments: ImmutableList<Comment>
    ) : DetailsScreenUiState()
}