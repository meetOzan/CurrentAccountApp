package com.mertozan.currentaccountapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "currentDebt")
    val currentDebt: Int,

    @ColumnInfo(name = "totalMoney")
    val totalMoney: Int,

    @ColumnInfo(name = "isInDebt")
    val isInDebt: Boolean,

)