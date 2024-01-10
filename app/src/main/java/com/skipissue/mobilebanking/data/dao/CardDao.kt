package com.skipissue.mobilebanking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.skipissue.mobilebanking.domain.entity.CardHistoryEntity

@Dao
interface CardDao {
    @Insert
    suspend fun insert(entities: List<CardHistoryEntity>)

    @Query("SELECT * FROM `transaction`")
    suspend fun getAll(): List<CardHistoryEntity>

    @Query("DELETE FROM `transaction`")
    suspend fun delete()

    @Query("SELECT * FROM `transaction` WHERE isSuccess = :isSuccess")
    suspend fun get(isSuccess: Boolean): List<CardHistoryEntity>
}