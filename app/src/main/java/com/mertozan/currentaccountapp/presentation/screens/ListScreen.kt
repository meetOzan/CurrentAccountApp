package com.mertozan.currentaccountapp.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.DashboardCustomize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.currentaccountapp.R
import com.mertozan.currentaccountapp.data.local.UserEntity
import com.mertozan.currentaccountapp.presentation.components.AddUserDialog
import com.mertozan.currentaccountapp.presentation.components.CaItem
import com.mertozan.currentaccountapp.presentation.components.CustomPlaceHolder
import com.mertozan.currentaccountapp.presentation.viewmodel.CaAction
import com.mertozan.currentaccountapp.presentation.viewmodel.CaUiState

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ListScreen(
    uiState: CaUiState,
    onAction: (CaAction) -> Unit,
    onAddCreditNavigate: (String, String, Int) -> Unit
) {

    var isFabEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Brush.linearGradient(listOf(Color.Red, Color.Blue)))
            ) {
                Text(
                    text = stringResource(R.string.current_account_app),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isFabEnabled = true
                },
                containerColor = Color.Blue
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp),
                    tint = Color.White
                )
            }
        }
    ) {
        it
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 72.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Customers",
                modifier = Modifier.padding(16.dp),
                color = Color.Black,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            if (uiState.users.isEmpty()) {
                CustomPlaceHolder(
                    text = stringResource(R.string.no_user_found),
                    icon = Icons.Rounded.DashboardCustomize,
                    imageColor = Color.Blue
                )
            }
            LazyColumn {
                items(uiState.users.size) { index ->
                    CaItem(
                        id = uiState.users[index].id,
                        name = uiState.users[index].name,
                        isInDebt = uiState.users[index].isInDebt,
                        totalMoney = uiState.users[index].totalMoney,
                        totalDebt = uiState.users[index].currentDebt,
                        addDebt = {
                            onAddCreditNavigate(
                                uiState.users[index].totalMoney.toString(),
                                uiState.users[index].currentDebt.toString(),
                                uiState.users[index].id
                            )
                        },
                        onAction = onAction,
                        uiState = uiState
                    )
                }
            }
        }
    }

    if (isFabEnabled) {
        AddUserDialog(
            onDismiss = { isFabEnabled = false },
            onAction = onAction
        )
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview
@Composable
fun PreviewOfListScreen() {
    ListScreen(
        uiState = CaUiState(
            users = listOf(
                UserEntity(
                    id = 1,
                    name = stringResource(R.string.mert),
                    totalMoney = 1000,
                    currentDebt = 0,
                    isInDebt = false
                )
            )
        ),
        onAction = {},
        onAddCreditNavigate = { _, _, _ -> }
    )
}