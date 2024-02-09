package com.mertozan.currentaccountapp.domain

import com.mertozan.currentaccountapp.data.local.UserEntity

interface CaRepository {

    fun getAllUsers(): List<UserEntity>

    fun insertUser(userEntity: UserEntity)

    fun deleteUser(userEntity: UserEntity)

    fun updateDebt(userId: Int, newDebt: Int)

    fun updateTotalMoney(userId: Int, newTotalMoney: Int)

    fun getUsersInDebt(isInDebt: Boolean): List<UserEntity>

    fun updateIsInDebt(userId: Int, isInDebt: Boolean)

}