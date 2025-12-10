package com.martanhub.cards

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform