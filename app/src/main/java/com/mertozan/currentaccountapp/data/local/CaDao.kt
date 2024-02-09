package com.mertozan.currentaccountapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CaDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserEntity>

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("UPDATE users SET currentDebt = :newDebt WHERE id = :userId")
    fun updateDebt(userId: Int, newDebt: Int)

    @Query("UPDATE users SET totalMoney = :newTotalMoney WHERE id = :userId")
    fun updateTotalMoney(userId: Int, newTotalMoney: Int)

    @Query("Update users SET isInDebt = :isInDebt WHERE id = :userId")
    fun updateIsInDebt(userId: Int, isInDebt: Boolean)



    @Query("SELECT * FROM users WHERE isInDebt = :isInDebt")
    fun getUsersInDebt(isInDebt: Boolean): List<UserEntity>

}