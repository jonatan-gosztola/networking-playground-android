package hu.csoniworks.networkingplayground.ui.screens.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListScreenViewModel() : ViewModel() {

    val uiState: StateFlow<ListScreenUiState?> = MutableStateFlow(null)

}