package com.martanhub.game.highlow.engine.bot

import com.martanhub.card.core.PlayingCard
import com.martanhub.card.core.ShuffledDeck
import com.martanhub.game.highlow.engine.CircularQueue
import com.martanhub.game.highlow.engine.Player

class RemainingCardsRankingBot(
    private val deck: ShuffledDeck
) : Player {
    private val cardsQueue = CircularQueue<PlayingCard>(capacity = deck.cards.size)

    override val name = "Bot: Remaining cards ranking"

    override suspend fun guess(card: PlayingCard): Boolean {
        cardsQueue.add(card)
        val remainingCards = deck.cards - cardsQueue.getAll().toSet()
        val remainingHigherCards =
            remainingCards.filter { it.rank.value > card.rank.value }.size
        val remainingLowerCards = remainingCards.filter { it.rank.value < card.rank.value }.size
        return remainingHigherCards > remainingLowerCards
    }

    override fun spectate(card: PlayingCard) {
        cardsQueue.add(card)
    }
}