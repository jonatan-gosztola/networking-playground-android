package hu.csoniworks.networkingplayground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.csoniworks.networkingplayground.data.DataSource
import hu.csoniworks.networkingplayground.ui.navigation.Screen
import hu.csoniworks.networkingplayground.ui.screens.details.DetailsScreenViewModel

class DetailsScreenViewModelFactory(
    private val key: Screen.DetailsScreen,
    private val dataSource: DataSource,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsScreenViewModel(
            id = key.id,
            dataSource = dataSource
        ) as T
    }
}