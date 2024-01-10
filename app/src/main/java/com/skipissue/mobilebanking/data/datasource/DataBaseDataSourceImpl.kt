package com.skipissue.mobilebanking.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.skipissue.mobilebanking.database.AppDataBase
import com.skipissue.mobilebanking.domain.entity.CardHistoryEntity
import com.skipissue.mobilebanking.domain.entity.getResponse.Data
import javax.inject.Inject

class DataBaseDataSourceImpl @Inject constructor(val database: AppDataBase) : DataBaseDataSource {
    override suspend fun insert(entities: List<CardHistoryEntity>) {
        withContext(Dispatchers.IO) {
            database.databaseDao().delete()
            database.databaseDao().insert(entities) }
    }

    override suspend fun getAll(): List<CardHistoryEntity> {
        return withContext(Dispatchers.IO){database.databaseDao().getAll()}
    }

    override suspend fun get(isSuccess: Boolean): List<CardHistoryEntity> {
        return withContext(Dispatchers.IO){database.databaseDao().get(isSuccess)}
    }

    override suspend fun getAllCard(): List<Data> {
        return withContext(Dispatchers.IO){database.cardsDao().getAll()}
    }

    override suspend fun getFromId(id: Long): List<Data> {
        return withContext(Dispatchers.IO){database.cardsDao().get(id)}
    }

    override suspend fun insertAllCard(cards: List<Data>) {
        return withContext(Dispatchers.IO){
            database.cardsDao().delete()
            database.cardsDao().insert(cards)}
    }

    override suspend fun insertCard(card: Data) {
        return withContext(Dispatchers.IO){database.cardsDao().insert(card)}
    }

    override suspend fun deleteCards() {
        withContext(Dispatchers.IO){
            database.cardsDao().delete()}
    }
}