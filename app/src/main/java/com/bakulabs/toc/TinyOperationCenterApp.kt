package com.bakulabs.toc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bakulabs.toc.ui.features.dashboard.DashboardScreen
import com.bakulabs.toc.ui.features.flights.FlightsScreen
import com.bakulabs.toc.ui.features.settings.SettingsScreen
import com.bakulabs.toc.ui.theme.TinyOperationCenterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TinyOperationCenterApp() {
    TinyOperationCenterTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                MediumTopAppBar(title = {
                    Text("Tiny Operation Center")
                })
            },
            bottomBar = {
                NavigationBar() {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    destinations.forEach { destination ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = destination.iconId),
                                    contentDescription = null
                                )
                            },
                            label = { Text(destination.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                            onClick = {
                                navController.navigate(destination.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = Destination.Dashboard.route,
                Modifier.padding(innerPadding).padding(12.dp)
            ) {
                composable("dashboard") { DashboardScreen() }
                composable("flights") { FlightsScreen() }
                composable("settings") { SettingsScreen() }
            }
        }
    }
}

val destinations = listOf(
    Destination.Dashboard,
    Destination.Flights,
    Destination.Settings
)

sealed class Destination(val route: String, val title: String, val iconId: Int) {
    object Dashboard : Destination("dashboard", "Dashboard", R.drawable.baseline_dashboard_24)
    object Flights : Destination("flights", "Flights", R.drawable.baseline_flight_24)
    object Settings : Destination("settings", "Settings", R.drawable.baseline_settings_24)
}