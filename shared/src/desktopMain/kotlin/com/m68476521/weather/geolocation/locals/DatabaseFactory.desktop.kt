package com.m68476521.weather.geolocation.locals

import androidx.room.Room
import androidx.room.RoomDatabase
import com.m68476521.weather.geolocation.data.locals.GeolocationDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<GeolocationDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.name")

        val appDataDir =
            when {
                os.contains("win") -> File(System.getenv("APPDATA"), "forecastly")
                os.contains("mac") -> File(userHome, "Library/Application Support/forecastly")
                else -> File(userHome, ".local/share/forecastly")
            }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }
        val dbFile = File(appDataDir, GeolocationDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}
