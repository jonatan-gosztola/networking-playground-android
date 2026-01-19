package hu.csoniworks.networkingplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import hu.csoniworks.networkingplayground.ui.navigation.Screen
import hu.csoniworks.networkingplayground.ui.screens.details.DetailsScreen
import hu.csoniworks.networkingplayground.ui.screens.list.ListScreen
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkingPlaygroundTheme {
                val backStack = rememberSaveable { mutableStateListOf<Screen>(Screen.ListScreen) }

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = { screenKey ->
                        when (screenKey) {
                            is Screen.ListScreen -> NavEntry(screenKey) {
                                ListScreen(
                                    onBackClicked = { backStack.removeLastOrNull() },
                                    onItemClicked = { id -> backStack.add(Screen.DetailsScreen(id) ) }
                                )
                            }

                            is Screen.DetailsScreen -> NavEntry(screenKey) {
                                DetailsScreen(
                                    id = screenKey.id,
                                    onBackClicked = { backStack.removeLastOrNull() }
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

