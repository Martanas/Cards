package com.martanhub.game.highlow.bot

import com.martanhub.card.PlayingCard
import com.martanhub.game.highlow.Player

class LastCardsRankingBot(
    private val count: Int
) : Player {
    init {
        require(count > 0) { "Bot must memorize at least one card" }
    }

    private val previousCards = mutableListOf<PlayingCard>()

    override val name = "Bot: Last cards ranking"

    override suspend fun guess(card: PlayingCard): Boolean {
        previousCards += card
        return true
    }

    private class Stack(val size: Int) {

    }
}