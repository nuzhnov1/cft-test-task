package com.sunman.binlist.data.dao

import androidx.room.*
import com.sunman.binlist.data.model.entities.CardEntity
import com.sunman.binlist.data.model.CardEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ICardDao {
    @Transaction
    @Query("SELECT * FROM Card WHERE id = :id")
    suspend fun getByBin(bin: Int): CardEntityModel?

    @Transaction
    @Query("SELECT * FROM Card")
    fun getAll(): Flow<List<CardEntityModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg cardEntity: CardEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateAll(vararg cardEntity: CardEntity)

    @Delete
    suspend fun deleteAll(vararg cardEntity: CardEntity)

    @Query("DELETE * FROM Card")
    suspend fun clear()
}
