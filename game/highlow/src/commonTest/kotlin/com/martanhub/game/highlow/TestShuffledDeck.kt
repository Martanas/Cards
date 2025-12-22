package com.martanhub.game.highlow

import com.martanhub.card.PlayingCard
import com.martanhub.card.ShuffledDeck

internal data class TestShuffledDeck(override val cards: List<PlayingCard>) : ShuffledDeck {
    companion object Companion {
        fun List<PlayingCard>.toTestShuffledDeck() = TestShuffledDeck(this)
    }
}