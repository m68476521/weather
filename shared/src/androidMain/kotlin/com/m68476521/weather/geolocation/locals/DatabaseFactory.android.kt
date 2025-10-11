package com.m68476521.weather.geolocation.locals

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.m68476521.weather.geolocation.data.locals.GeolocationDatabase

actual class DatabaseFactory(
    private val context: Context,
) {
    actual fun create(): RoomDatabase.Builder<GeolocationDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(GeolocationDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = context,
            name = dbFile.absolutePath,
        )
    }
}
