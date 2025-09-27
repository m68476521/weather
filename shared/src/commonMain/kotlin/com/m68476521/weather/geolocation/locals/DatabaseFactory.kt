package com.m68476521.weather.geolocation.locals

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<GeolocationDatabase>
}