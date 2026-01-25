package hu.csoniworks.networkingplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import hu.csoniworks.networkingplayground.di.DetailsScreenViewModelFactory
import hu.csoniworks.networkingplayground.di.ListScreenViewModelProvider
import hu.csoniworks.networkingplayground.ui.navigation.Screen
import hu.csoniworks.networkingplayground.ui.screens.details.DetailsScreen
import hu.csoniworks.networkingplayground.ui.screens.details.DetailsScreenViewModel
import hu.csoniworks.networkingplayground.ui.screens.list.ListScreen
import hu.csoniworks.networkingplayground.ui.screens.list.ListScreenViewModel
import hu.csoniworks.networkingplayground.ui.theme.NetworkingPlaygroundTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NetworkingPlaygroundTheme {
//                val backStack = rememberSaveable { mutableStateListOf<Screen>(Screen.ListScreen) }

                val backStack = rememberNavBackStack(Screen.ListScreen)

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = { screenKey ->
                        when (screenKey) {
                            is Screen.ListScreen -> NavEntry(screenKey) {
                                ListScreen(
                                    viewModel = viewModel<ListScreenViewModel>(
                                        factory = ListScreenViewModelProvider(dataSource = get())
                                    ),
                                    onBackClicked = { backStack.removeLastOrNull() },
                                    onItemClicked = { id -> backStack.add(Screen.DetailsScreen(id)) }
                                )
                            }

                            is Screen.DetailsScreen -> NavEntry(screenKey) {
                                DetailsScreen(
                                    viewModel = viewModel<DetailsScreenViewModel>(
                                        factory = DetailsScreenViewModelFactory(
                                            key = screenKey,
                                            dataSource = get()
                                        )
                                    ),
                                    onBackClicked = { backStack.removeLastOrNull() }
                                )
                            }
                            else -> throw IllegalStateException("No such nav key")
                        }
                    }
                )
            }
        }
    }
}

