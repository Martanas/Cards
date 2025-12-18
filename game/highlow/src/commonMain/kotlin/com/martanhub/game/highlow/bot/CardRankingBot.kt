package com.martanhub.game.highlow.bot

import com.martanhub.card.PlayingCard
import com.martanhub.card.Rank
import com.martanhub.game.highlow.Player

class CardRankingBot(
    private val rankFactory: Rank.Factory
) : Player {
    override val name = "Bot: Card ranking"

    override suspend fun guess(card: PlayingCard): Boolean {
        val value = card.rank.value
        val highestValue = rankFactory.highest().value
        val lowestValue = rankFactory.lowest().value
        return highestValue - value > value - lowestValue
    }
}