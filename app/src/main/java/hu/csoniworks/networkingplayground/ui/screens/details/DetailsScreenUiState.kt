package hu.csoniworks.networkingplayground.ui.screens.details

sealed class DetailsScreenUiState {
    data object Loading : DetailsScreenUiState()

    data class Default(
        val id: String
    ) : DetailsScreenUiState()
}