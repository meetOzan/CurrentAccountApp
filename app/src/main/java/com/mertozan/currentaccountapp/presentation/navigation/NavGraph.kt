package com.mertozan.currentaccountapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mertozan.currentaccountapp.presentation.screens.ListScreen
import com.mertozan.currentaccountapp.presentation.screens.SplashScreen
import com.mertozan.currentaccountapp.presentation.screens.TakeCreditScreen
import com.mertozan.currentaccountapp.presentation.viewmodel.CaAction
import com.mertozan.currentaccountapp.presentation.viewmodel.CaViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun CiroNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = SplashScreen.route
    ) {
        listScreen { money, credit, id ->
            navController.navigate(
                TakeCredit.navigateWithArgs(money ,credit, id)
            )
        }
        splashScreen {
            navController.navigate(ListScreen.route) {
                popUpTo(SplashScreen.route) {
                    inclusive = true
                }
            }
        }
        takeCreditScreen {
            navController.navigate(ListScreen.route) {
                popUpTo(TakeCredit.route) {
                    inclusive = true
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun NavGraphBuilder.listScreen(
    onAddCreditNavigate: (String, String, Int) -> Unit,
) {
    composable(route = ListScreen.route) {

        val viewModel = hiltViewModel<CaViewModel>()
        val uiState = viewModel.caUiState.collectAsState().value


        LaunchedEffect(key1 = true) {
            viewModel.onAction(CaAction.GetAllUser)
        }

        ListScreen(
            uiState = uiState,
            onAction = viewModel::onAction,
            onAddCreditNavigate = onAddCreditNavigate
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.splashScreen(
    onListScreenNavigate: () -> Unit
) {
    composable(route = SplashScreen.route) {

        SplashScreen(
            onNavigate = onListScreenNavigate
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.takeCreditScreen(
    onListScreenNavigate: () -> Unit
) {
    composable(
        route = TakeCredit.routeWithArgs,
        arguments = TakeCredit.args
    ) {

        val viewModel = hiltViewModel<CaViewModel>()
        val uiState = viewModel.caUiState.collectAsState().value

        TakeCreditScreen(
            onNavigate = onListScreenNavigate,
            onAction = viewModel::onAction,
            uiState = uiState
        )
    }
}