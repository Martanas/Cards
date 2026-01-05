package com.martanhub.game.highlow.bot

import com.martanhub.card.PlayingCard
import com.martanhub.card.Rank
import com.martanhub.game.highlow.CircularQueue
import com.martanhub.game.highlow.Player

class LastCardsAvgRankingBot(
    private val rankFactory: Rank.Factory,
    count: Int
) : Player {
    init {
        require(count > 0) { "Bot must memorize at least one card" }
    }

    private val cardsQueue = CircularQueue<PlayingCard>(capacity = count)

    override val name = "Bot: Last cards ranking"

    override suspend fun guess(card: PlayingCard): Boolean {
        val avg = cardsQueue.getAll()
            .sumOf { it.rank.value }
            .takeIf { it > 0 }
            ?.div(cardsQueue.getAll().size)
            ?: card.rank.value
        cardsQueue.add(card)
        val highestValue = rankFactory.highest().value
        val lowestValue = rankFactory.lowest().value
        return highestValue - avg > avg - lowestValue
    }

    override fun spectate(card: PlayingCard) {
        cardsQueue.add(card)
    }
}