package hu.csoniworks.networkingplayground.ui.screens.list

sealed class ListScreenUiState {
    data object Loading : ListScreenUiState()

    data class Default(
        val list: List<ListItem>
    ) : ListScreenUiState()
}