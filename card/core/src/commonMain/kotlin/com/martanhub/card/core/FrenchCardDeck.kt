package com.martanhub.card.core

import kotlin.jvm.JvmInline

@JvmInline
value class FrenchCardDeck(
    override val cards: List<FrenchPlayingCard>
) : Deck {
    init {
        require(cards.size == 52) { "French card decks must have 52 cards, was: ${cards.size}" }
    }

    companion object {
        fun create(): Deck = FrenchCardDeck(
            FrenchRank.entries.map { rank ->
                FrenchSuit.entries.map { suit ->
                    FrenchPlayingCard(rank, suit)
                }
            }.flatten()
        )
    }
}