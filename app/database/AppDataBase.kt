package uz.gita.lesson40.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.lesson40.data.dao.CardDao
import uz.gita.lesson40.data.dao.CardsDao
import uz.gita.lesson40.domain.entity.CardHistoryEntity
import uz.gita.lesson40.domain.entity.getResponse.Data

@Database(entities = [CardHistoryEntity::class, Data::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun databaseDao(): CardDao
    abstract fun cardsDao(): CardsDao
}