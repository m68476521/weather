package com.m68476521.weather.geolocation.data.locals

import GeoLocationEntity
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface GeoLocationDao {
    @Upsert
    @Transaction
    suspend fun upsertGeoLocation(geolocationEntity: GeoLocationEntity)

    @Query("Select * from geolocation_table Limit 1")
    fun geoLocation(): Flow<GeoLocationEntity?>

    @Query("Delete From geolocation_table")
    @Transaction
    suspend fun clearGeoLocation()
}
