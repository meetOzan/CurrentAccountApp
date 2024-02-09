package com.mertozan.currentaccountapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class CaDatabase : RoomDatabase(){
    abstract fun caDao(): CaDao
}