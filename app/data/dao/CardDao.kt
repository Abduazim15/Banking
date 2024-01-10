package uz.gita.lesson40.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.gita.lesson40.domain.entity.CardHistoryEntity

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