package hu.csoniworks.networkingplayground.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.csoniworks.networkingplayground.data.DataSource
import hu.csoniworks.networkingplayground.ui.screens.list.ListScreenViewModel

class ListScreenViewModelProvider(
    private val dataSource: DataSource
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListScreenViewModel(
            dataSource = dataSource
        ) as T
    }
}