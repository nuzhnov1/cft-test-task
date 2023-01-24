package com.sunman.binlist.data.dao

import androidx.room.*
import com.sunman.binlist.data.model.entities.BankEntity

@Dao
interface IBankDao {
    @Query("SELECT COUNT(*) FROM Card WHERE bankName = :bankName")
    suspend fun getUsagesCount(bankName: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg bankEntity: BankEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateAll(vararg bankEntity: BankEntity)

    @Delete
    suspend fun deleteAll(vararg bankEntity: BankEntity)

    @Query("DELETE * FROM Bank")
    suspend fun clear()
}
