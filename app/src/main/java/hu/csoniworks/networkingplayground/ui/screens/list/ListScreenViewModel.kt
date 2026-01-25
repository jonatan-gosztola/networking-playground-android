package hu.csoniworks.networkingplayground.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.csoniworks.networkingplayground.data.DataSource
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListScreenViewModel(
    private val dataSource: DataSource
) : ViewModel() {

    val uiState: StateFlow<ListScreenUiState> = combine(
        dataSource.hasError(),
        dataSource.getPosts()
    ) { hasError, posts ->
        when {
            hasError -> ListScreenUiState.Error
            posts.isEmpty() -> ListScreenUiState.Loading
            else -> ListScreenUiState.Default(
                list = posts.toImmutableList()
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ListScreenUiState.Loading)

    init {
        viewModelScope.launch {
            dataSource.fetchPosts()
        }
    }
}