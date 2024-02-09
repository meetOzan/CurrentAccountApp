package com.mertozan.currentaccountapp.presentation.viewmodel

import com.mertozan.currentaccountapp.data.local.UserEntity

sealed class CaAction {

    data object GetAllUser : CaAction()

    data class InsertUser(val userEntity: UserEntity) : CaAction()

    data class DeleteUser(val userEntity: UserEntity) : CaAction()

    data class UpdateDebt(val newDebt: Int) : CaAction()

    data class UpdateTotalMoney(val newTotalMoney: Int) : CaAction()

    data class GetUsersInDebt(val isInDebt: Boolean) : CaAction()

    data class ChangeCreditAmount(val creditAmount: String) : CaAction()

    data class PayDebt(val debtAmount: String) : CaAction()

    data class UpdatePayDebt(val id: Int, val newDebt: Int) : CaAction()

    data class UpdatePayDebtTotalMoney(val id: Int, val newTotalMoney: Int) : CaAction()

    data class UpdateIsInDebt(val id: Int, val isInDebt: Boolean) : CaAction()
}