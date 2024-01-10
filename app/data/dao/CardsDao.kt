package uz.gita.lesson40.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.gita.lesson40.domain.entity.getResponse.Data

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