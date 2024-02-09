package com.mertozan.currentaccountapp.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mertozan.currentaccountapp.common.Constants.ARGS_DEBT
import com.mertozan.currentaccountapp.common.Constants.ARGS_ID
import com.mertozan.currentaccountapp.common.Constants.ARGS_MONEY

interface Destination {
    val route: String
}

object SplashScreen : Destination {
    override val route = "splash_screen"
}

object ListScreen : Destination {
    override val route = "list_screen"
}

object TakeCredit : Destination {
    override val route = "take_credit_screen"
    fun navigateWithArgs(
        money: String,
        name: String,
        id: Int
    ): String = "$route/$money/$name/$id"

    val routeWithArgs = "$route/{$ARGS_MONEY}/{$ARGS_DEBT}/{$ARGS_ID}"
    val args = listOf(
        navArgument(ARGS_MONEY) { type = NavType.StringType },
        navArgument(ARGS_DEBT) { type = NavType.StringType },
        navArgument(ARGS_ID) { type = NavType.IntType }
    )
}