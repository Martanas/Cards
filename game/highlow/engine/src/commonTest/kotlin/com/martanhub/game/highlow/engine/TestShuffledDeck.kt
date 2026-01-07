package com.martanhub.game.highlow.engine

import com.martanhub.card.core.PlayingCard
import com.martanhub.card.core.ShuffledDeck

internal data class TestShuffledDeck(override val cards: List<PlayingCard>) : ShuffledDeck {
    companion object Companion {
        fun List<PlayingCard>.toTestShuffledDeck() = TestShuffledDeck(this)
    }
}