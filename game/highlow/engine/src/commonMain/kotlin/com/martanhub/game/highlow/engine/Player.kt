package com.martanhub.game.highlow.engine

import com.martanhub.card.core.PlayingCard

interface Player {
    val name: String
    suspend fun guess(card: PlayingCard): Boolean

    fun spectate(card: PlayingCard)
}