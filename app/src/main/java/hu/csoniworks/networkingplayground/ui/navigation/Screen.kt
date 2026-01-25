package hu.csoniworks.networkingplayground.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {

    @Serializable
    data object ListScreen : Screen

    @Serializable
    data class DetailsScreen(val id: Int) : Screen

}