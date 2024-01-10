package com.skipissue.mobilebanking.data.repository

import com.skipissue.mobilebanking.data.datasource.DataBaseDataSource
import com.skipissue.mobilebanking.domain.entity.CardHistoryEntity
import com.skipissue.mobilebanking.domain.entity.getResponse.Data
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(val dataSource: DataBaseDataSource): DatabaseRepository {
    override suspend fun insert(entities: List<CardHistoryEntity>) {
        dataSource.insert(entities)
    }

    override suspend fun getAll(): List<CardHistoryEntity> {
        return dataSource.getAll()
    }

    override suspend fun get(isSuccess: Boolean): List<CardHistoryEntity> {
        return dataSource.get(isSuccess)
    }

    override suspend fun getAllCard(): List<Data> {
        return dataSource.getAllCard()
    }

    override suspend fun getFromId(id: Long): List<Data> {
        return dataSource.getFromId(id)
    }

    override suspend fun insertAllCard(cards: List<Data>) {
        return dataSource.insertAllCard(cards)
    }

    override suspend fun insertCard(card: Data) {
        return dataSource.insertCard(card)
    }

    override suspend fun deleteCards() {
        return dataSource.deleteCards()
    }
}