package com.nuzhnov.bankcard.data.dao

import androidx.room.*
import com.nuzhnov.bankcard.data.model.entities.CountryEntity

@Dao
interface ICountryDao {
    @Query("SELECT COUNT(*) FROM Card WHERE countryNumber = :countryNumber")
    suspend fun getUsagesCount(countryNumber: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg countryEntity: CountryEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateAll(vararg countryEntity: CountryEntity)

    @Delete
    suspend fun deleteAll(vararg countryEntity: CountryEntity)

    @Query("DELETE FROM Country")
    suspend fun clear()
}
