package uz.gita.lesson40.data.repository

import uz.gita.lesson40.domain.entity.CardHistoryEntity
import uz.gita.lesson40.domain.entity.getResponse.Data

interface DatabaseRepository {
    suspend fun insert(entities: List<CardHistoryEntity>)
    suspend fun getAll(): List<CardHistoryEntity>
    suspend fun get(isSuccess: Boolean): List<CardHistoryEntity>
    suspend fun getAllCard(): List<Data>
    suspend fun getFromId(id: Long): List<Data>
    suspend fun insertAllCard(cards: List<Data>)
    suspend fun insertCard(card: Data)
    suspend fun deleteCards()
}