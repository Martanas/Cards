package com.martanhub.card.core

class ShuffleMachine {
    fun shuffle(deck: Deck): ShuffledDeck {
        return MachineShuffledDeck(deck.cards.shuffled())
    }

    private data class MachineShuffledDeck(
        override val cards: List<PlayingCard>
    ) : ShuffledDeck
}