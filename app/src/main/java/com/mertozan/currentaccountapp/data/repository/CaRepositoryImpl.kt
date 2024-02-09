package com.mertozan.currentaccountapp.data.repository

import com.mertozan.currentaccountapp.data.local.CaDao
import com.mertozan.currentaccountapp.data.local.UserEntity
import com.mertozan.currentaccountapp.domain.CaRepository
import javax.inject.Inject

class CaRepositoryImpl @Inject constructor(
    private val caDao: CaDao,
) : CaRepository {

    override fun getAllUsers(): List<UserEntity> {
        return caDao.getAllUsers()
    }

    override fun insertUser(userEntity: UserEntity) {
        caDao.insertUser(userEntity)
    }

    override fun deleteUser(userEntity: UserEntity) {
        caDao.deleteUser(userEntity)
    }

    override fun updateDebt(userId: Int, newDebt: Int) {
        caDao.updateDebt(userId, newDebt)
    }

    override fun updateTotalMoney(userId: Int, newTotalMoney: Int) {
        caDao.updateTotalMoney(userId, newTotalMoney)
    }

    override fun getUsersInDebt(isInDebt: Boolean): List<UserEntity> {
        return caDao.getUsersInDebt(isInDebt)
    }

    override fun updateIsInDebt(userId: Int, isInDebt: Boolean) {
        caDao.updateIsInDebt(userId, isInDebt)
    }

}
