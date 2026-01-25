package hu.csoniworks.networkingplayground.ui.screens.list

import hu.csoniworks.networkingplayground.data.models.Post
import kotlinx.collections.immutable.ImmutableList

sealed class ListScreenUiState {
    data object Loading : ListScreenUiState()

    data object Error : ListScreenUiState()

    data class Default(
        val list: ImmutableList<Post>
    ) : ListScreenUiState()
}