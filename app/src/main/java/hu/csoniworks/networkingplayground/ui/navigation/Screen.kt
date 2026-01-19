package hu.csoniworks.networkingplayground.ui.navigation

sealed class Screen {

    data object ListScreen : Screen()
    data class DetailsScreen(val id: String) : Screen() {
        companion object {
            const val EXTRAS_ID = "EXTRAS_ID"
        }
    }

}