package com.m68476521.weather

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform