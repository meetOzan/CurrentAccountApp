package com.mertozan.currentaccountapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.currentaccountapp.common.Constants.ARGS_ID
import com.mertozan.currentaccountapp.common.Constants.ARGS_DEBT
import com.mertozan.currentaccountapp.common.Constants.ARGS_MONEY
import com.mertozan.currentaccountapp.data.local.UserEntity
import com.mertozan.currentaccountapp.domain.CaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaViewModel @Inject constructor(
    private val caRepository: CaRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _caUiState = MutableStateFlow(CaUiState.initial())
    val caUiState = _caUiState.asStateFlow()

    private val currentDebt = savedStateHandle.get<String>(key = ARGS_DEBT)
    private val currentMoney = savedStateHandle.get<String>(key = ARGS_MONEY)
    private val id = savedStateHandle.get<Int>(key = ARGS_ID)

    fun onAction(action: CaAction) {
        when (action) {
            is CaAction.GetAllUser -> getAllUsers()
            is CaAction.InsertUser -> insertUser(action.userEntity)
            is CaAction.DeleteUser -> deleteUser(action.userEntity)
            is CaAction.UpdateDebt -> updateDebt(action.newDebt)
            is CaAction.UpdateTotalMoney -> updateTotalMoney(action.newTotalMoney)
            is CaAction.GetUsersInDebt -> getUsersInDebt(action.isInDebt)
            is CaAction.ChangeCreditAmount -> changeCreditAmount(action.creditAmount)
            is CaAction.PayDebt -> payDebt(action.debtAmount)
            is CaAction.UpdatePayDebt -> updatePayDebt(action.id, action.newDebt)
            is CaAction.UpdatePayDebtTotalMoney -> updatePayDebtTotalMoney(action.id, action.newTotalMoney)
            is CaAction.UpdateIsInDebt -> updateIsInDebt(action.id, action.isInDebt)
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            _caUiState.value = _caUiState.value.copy(users = caRepository.getAllUsers())
        }
    }

    private fun insertUser(userEntity: UserEntity) {
        viewModelScope.launch {
            caRepository.insertUser(userEntity)
            getAllUsers()
        }
    }

    private fun deleteUser(userEntity: UserEntity) {
        viewModelScope.launch {
            caRepository.deleteUser(userEntity)
            getAllUsers()
        }
    }

    private fun updateDebt(newDebt: Int) {
        viewModelScope.launch {
            if (id != null) {
                caRepository.updateDebt(
                    id,
                    (currentDebt?.toInt()!! + newDebt)
                )
            }
            getAllUsers()
        }
    }

    private fun updatePayDebt(id: Int, newDebt: Int) {
        viewModelScope.launch {
            caRepository.updateDebt(id, newDebt)
            getAllUsers()
        }
    }


    private fun updateTotalMoney(newTotalMoney: Int) {
        viewModelScope.launch {
            if (id != null) {
                caRepository.updateTotalMoney(id,currentMoney!!.toInt() + newTotalMoney)
            }
            getAllUsers()
        }
    }

    private fun updatePayDebtTotalMoney(id: Int, newTotalMoney: Int) {
        viewModelScope.launch {
            caRepository.updateTotalMoney(id, newTotalMoney)
            getAllUsers()
        }
    }

    private fun getUsersInDebt(isInDebt: Boolean) {
        viewModelScope.launch {
            _caUiState.value = _caUiState.value.copy(users = caRepository.getUsersInDebt(isInDebt))
        }
    }

    private fun changeCreditAmount(creditAmount: String) {
        viewModelScope.launch {
            _caUiState.value = _caUiState.value.copy(creditAmount = creditAmount)
        }
    }

    private fun payDebt(payDebt: String) {
        viewModelScope.launch {
            _caUiState.value = _caUiState.value.copy(payDebt = payDebt)
        }
    }

    private fun updateIsInDebt(id: Int, isInDebt: Boolean) {
        viewModelScope.launch {
            caRepository.updateIsInDebt(id, isInDebt)
            getAllUsers()
        }
    }

}

data class CaUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val users: List<UserEntity> = emptyList(),
    val creditAmount: String = "",
    val currentDebt: String = "",
    val payDebt: String = ""
) {
    companion object {
        fun initial() = CaUiState(
            isLoading = true
        )
    }
}