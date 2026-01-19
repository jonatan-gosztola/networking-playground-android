package hu.csoniworks.networkingplayground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.csoniworks.networkingplayground.ui.navigation.Screen
import hu.csoniworks.networkingplayground.ui.screens.details.DetailsScreenViewModel

class DetailsScreenViewModelFactory(
    private val key: Screen.DetailsScreen,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsScreenViewModel(key.id) as T
    }
}