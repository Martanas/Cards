package com.martanhub.game.highlow

import com.martanhub.card.PlayingCard

interface Player {
    val name: String
    suspend fun guess(card: PlayingCard): Boolean
}