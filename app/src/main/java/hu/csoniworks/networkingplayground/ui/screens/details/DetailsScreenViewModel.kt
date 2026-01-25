package hu.csoniworks.networkingplayground.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.csoniworks.networkingplayground.data.DataSource
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    id: Int,
    dataSource: DataSource,
) : ViewModel() {

    val uiState: StateFlow<DetailsScreenUiState?> = combine(
        dataSource.hasError(),
        dataSource.getPosts().map { it.first { it.id == id } },
        dataSource.getComments(id)
    ) { hasError, post, comments ->
        when {
            hasError -> DetailsScreenUiState.Error
            comments.isEmpty() -> DetailsScreenUiState.Loading
            else -> DetailsScreenUiState.Default(
                id = id,
                post = post,
                comments = comments.toImmutableList()
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DetailsScreenUiState.Loading)

    init {
        viewModelScope.launch {
            dataSource.fetchComments()
        }
    }
}