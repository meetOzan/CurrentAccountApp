package com.mertozan.currentaccountapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.currentaccountapp.R
import com.mertozan.currentaccountapp.presentation.viewmodel.CaAction
import com.mertozan.currentaccountapp.presentation.viewmodel.CaUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayDebtAlertDialog(
    id: Int,
    totalMoney: Int ,
    totalDebt: Int ,
    uiState: CaUiState,
    onAction: (CaAction) -> Unit,
    onDismiss: () -> Unit
) {

    var showError by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val control =
        uiState.payDebt == "" || uiState.payDebt.toInt() > totalMoney || uiState.payDebt.toInt() > totalDebt

    AlertDialog(
        onDismissRequest = { onDismiss.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(ShapeDefaults.Large)
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.pay_debt),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "Total Money: $totalMoney$",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total Debt: $totalDebt$",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = uiState.payDebt,
                onValueChange = {
                    onAction(CaAction.PayDebt(it))
                },
                label = { Text(stringResource(id = R.string.pay_debt)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (showError) {
                Text(
                    stringResource(R.string.error_pay_debt_cannot_be_null_or_greater_than_total_money_or_less_than_total_debt),
                    color = Color.Red
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {
                    onDismiss()
                    keyboardController?.hide()
                }) {
                    Text(text = stringResource(R.string.cancel), color = MaterialTheme.colorScheme.primary)
                }
                TextButton(
                    onClick = {
                        showError = control
                        if (!showError) {
                            onAction(CaAction.UpdatePayDebt(id,totalDebt - uiState.payDebt.toInt()))
                            onAction(CaAction.UpdatePayDebtTotalMoney(id, totalMoney - uiState.payDebt.toInt()))
                            onDismiss()
                            keyboardController?.hide()
                        }
                    }
                ) {
                    Text(color = MaterialTheme.colorScheme.primary
                        , text = stringResource(id = R.string.pay_debt))
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewOfPayDebt() {
    PayDebtAlertDialog(
        id = 1,
        totalMoney = 100,
        totalDebt = 0,
        uiState = CaUiState(),
        onAction = {},
        onDismiss = {}
    )
}
