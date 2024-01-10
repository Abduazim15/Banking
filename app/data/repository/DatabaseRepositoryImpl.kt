package uz.gita.lesson40.data.repository

import uz.gita.lesson40.data.datasource.DataBaseDataSource
import uz.gita.lesson40.domain.entity.CardHistoryEntity
import uz.gita.lesson40.domain.entity.getResponse.Data
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