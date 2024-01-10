package com.skipissue.mobilebanking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.skipissue.mobilebanking.domain.entity.getResponse.Data

@Dao
interface CardsDao {
    @Insert
    suspend fun insert(entities: List<Data>)

    @Insert
    suspend fun insert(entity: Data)

    @Query("DELETE FROM cards")
    suspend fun delete()

    @Query("SELECT * FROM `cards`")
    suspend fun getAll(): List<Data>

    @Query("SELECT * FROM `cards` WHERE qId = :id")
    suspend fun get(id: Long): List<Data>
}