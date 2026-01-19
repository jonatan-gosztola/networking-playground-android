package hu.csoniworks.networkingplayground.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.csoniworks.networkingplayground.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsScreenViewModel(
    id: String,
): ViewModel() {

    val uiState: StateFlow<DetailsScreenUiState?> = MutableStateFlow(DetailsScreenUiState.Loading)
}