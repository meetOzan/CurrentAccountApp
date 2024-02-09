package com.mertozan.currentaccountapp.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.currentaccountapp.R
import com.mertozan.currentaccountapp.presentation.viewmodel.CaAction
import com.mertozan.currentaccountapp.presentation.viewmodel.CaUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaItem(
    id: Int,
    name: String,
    isInDebt: Boolean,
    totalMoney: Int,
    totalDebt: Int,
    uiState: CaUiState,
    onAction: (CaAction) -> Unit,
    addDebt: () -> Unit
) {

    LaunchedEffect(true) {
        if (totalDebt >= 500000) {
            onAction(CaAction.UpdateIsInDebt(id, true))
        }else{
            onAction(CaAction.UpdateIsInDebt(id, false))
        }
    }

    LaunchedEffect(totalDebt){
        if (totalDebt >= 500000) {
            onAction(CaAction.UpdateIsInDebt(id, true))
        }else{
            onAction(CaAction.UpdateIsInDebt(id, false))
        }
    }

    var isExtended by remember {
        mutableStateOf(false)
    }

    var isPayDebtEnabled by remember { mutableStateOf(false) }

    Card(
        onClick = { isExtended = !isExtended },
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isExtended) 280.dp else 120.dp)
            .padding(8.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight()
                    .padding(end = 8.dp)
                    .background(
                        if (isInDebt) Color.Red else Color.Green
                    )
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = 0.8f,
                            stiffness = 100f
                        ),
                    )
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.customer_name, name),
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp)
                        .fillMaxWidth(),
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                if (isExtended) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(top = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.MoneyOff, contentDescription = null)
                                Text(
                                    text = stringResource(R.string.total_dept, totalDebt),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .padding(top = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Money,
                                    contentDescription = null
                                )
                                Text(
                                    text = stringResource(R.string.total_money, totalMoney),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(top = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = null)
                                Text(
                                    text = stringResource(
                                        R.string.is_able_to_credit,
                                        if (isInDebt) {
                                            stringResource(R.string.no)
                                        } else {
                                            stringResource(R.string.yes)
                                        }
                                    ),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(top = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
                                Text(
                                    text = stringResource(R.string.balance, totalMoney - totalDebt),
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                            ) {
                                ElevatedButton(
                                    onClick = addDebt,
                                    enabled = !isInDebt,
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        containerColor = if (isInDebt) Color.Gray else Color.White
                                    )
                                ) {
                                    Text(text = stringResource(R.string.take_credit))
                                }
                                ElevatedButton(onClick = { isPayDebtEnabled = true }) {
                                    Text(text = stringResource(R.string.pay_debt))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (isPayDebtEnabled) {
        PayDebtAlertDialog(
            id = id,
            uiState = uiState,
            onAction = onAction,
            totalMoney = totalMoney,
            totalDebt = totalDebt,
            onDismiss = {
                isPayDebtEnabled = false
                onAction(CaAction.PayDebt(""))
            }
        )
    }
}

@Preview
@Composable
fun PreviewOfCaItem() {
    CaItem(
        id = 1,
        name = "Mert",
        isInDebt = false,
        totalMoney = 231203,
        totalDebt = 321419,
        addDebt = { },
        onAction = {},
        uiState = CaUiState()
    )
}