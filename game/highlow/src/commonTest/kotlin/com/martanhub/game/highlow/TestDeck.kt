package com.martanhub.game.highlow

import com.martanhub.card.Deck
import com.martanhub.card.PlayingCard

internal data class TestDeck(override val cards: List<PlayingCard>) : Deck {
    companion object {
        fun List<PlayingCard>.toTestDeck() = TestDeck(this)
    }
}