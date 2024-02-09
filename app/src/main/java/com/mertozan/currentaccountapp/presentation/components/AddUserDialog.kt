package com.mertozan.currentaccountapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.currentaccountapp.data.local.UserEntity
import com.mertozan.currentaccountapp.presentation.viewmodel.CaAction

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserDialog(
    onDismiss: () -> Unit,
    onAction: (CaAction) -> Unit,
) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var totalDebt by remember { mutableStateOf(TextFieldValue()) }
    var totalMoney by remember { mutableStateOf(TextFieldValue()) }
    var isInDept by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { onDismiss },
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add User",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.padding(top = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = totalDebt,
                onValueChange = { totalDebt = it },
                label = { Text("Total Debt") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = totalMoney,
                onValueChange = { totalMoney = it },
                label = { Text("Total Money") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancel", color = MaterialTheme.colorScheme.primary)
                }

                TextButton(
                    onClick = {
                        onAction(
                            CaAction.InsertUser(
                                UserEntity(
                                    name = name.text,
                                    currentDebt = totalDebt.text.toInt(),
                                    totalMoney = totalMoney.text.toInt(),
                                    isInDebt = if (totalMoney.text.toInt() < totalDebt.text.toInt()) true else isInDept
                                )
                            )
                        )
                        onDismiss()
                    }
                ) {
                    Text("Add", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    AddUserDialog(
        onDismiss = { },
        onAction = { }
    )
}
