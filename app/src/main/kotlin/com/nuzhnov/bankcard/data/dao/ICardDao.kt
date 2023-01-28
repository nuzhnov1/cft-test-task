package com.nuzhnov.bankcard.data.dao

import androidx.room.*
import com.nuzhnov.bankcard.data.model.CardEntityModel
import com.nuzhnov.bankcard.data.model.entities.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ICardDao {
    @Transaction
    @Query("SELECT * FROM Card WHERE bin = :bin")
    suspend fun getByBin(bin: String): CardEntityModel?

    @Transaction
    @Query("SELECT * FROM Card")
    fun getAll(): Flow<List<CardEntityModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg cardEntity: CardEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateAll(vararg cardEntity: CardEntity)

    @Delete
    suspend fun deleteAll(vararg cardEntity: CardEntity)

    @Query("DELETE FROM Card")
    suspend fun clear()
}
