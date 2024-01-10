package com.skipissue.mobilebanking.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.skipissue.mobilebanking.data.dao.CardDao
import com.skipissue.mobilebanking.data.dao.CardsDao
import com.skipissue.mobilebanking.domain.entity.CardHistoryEntity
import com.skipissue.mobilebanking.domain.entity.getResponse.Data

@Database(entities = [CardHistoryEntity::class, Data::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun databaseDao(): CardDao
    abstract fun cardsDao(): CardsDao
}