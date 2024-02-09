package com.mertozan.currentaccountapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mertozan.currentaccountapp.presentation.viewmodel.CaAction
import com.mertozan.currentaccountapp.presentation.viewmodel.CaUiState

@Composable
fun TakeCreditScreen(
    onNavigate: () -> Unit,
    onAction: (CaAction) -> Unit,
    uiState: CaUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Take Credit",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        TextField(
            value = uiState.creditAmount,
            onValueChange = { newCredit ->
                onAction(CaAction.ChangeCreditAmount(newCredit))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.None
            ),
            visualTransformation = VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CreditButton("50000", onAction)
            CreditButton("100000", onAction)
            CreditButton("150000", onAction)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onNavigate()
                onAction(CaAction.UpdateDebt(uiState.creditAmount.toInt()))
                onAction(CaAction.UpdateTotalMoney(uiState.creditAmount.toInt()))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Take Credit")
        }
    }
}

@Composable
fun CreditButton(amount: String, onAction: (CaAction) -> Unit) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .clip(MaterialTheme.shapes.medium)
            .padding(8.dp)
            .clickable {
                onAction(CaAction.ChangeCreditAmount(amount))
            }
    ) {
        Text(
            text = amount,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}