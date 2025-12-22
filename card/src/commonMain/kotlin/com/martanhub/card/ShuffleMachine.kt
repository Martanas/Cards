package com.martanhub.card

class ShuffleMachine {
    fun shuffle(deck: Deck): ShuffledDeck {
        return MachineShuffledDeck(deck.cards.shuffled())
    }

    private data class MachineShuffledDeck(
        override val cards: List<PlayingCard>
    ) : ShuffledDeck
}