package com.martanhub.game.highlow.engine.bot

import com.martanhub.card.core.PlayingCard
import com.martanhub.card.core.Rank
import com.martanhub.game.highlow.engine.Player

class CurrentCardRankingBot(
    private val rankFactory: Rank.Factory
) : Player {
    override val name = "Bot: Current card ranking"

    override suspend fun guess(card: PlayingCard): Boolean {
        val value = card.rank.value
        val highestValue = rankFactory.highest().value
        val lowestValue = rankFactory.lowest().value
        return highestValue - value > value - lowestValue
    }

    override fun spectate(card: PlayingCard) {
        // Do nothing
    }
}