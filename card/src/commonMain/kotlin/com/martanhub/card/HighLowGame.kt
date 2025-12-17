package com.martanhub.card

class HighLowGame(deck: List<PlayingCard>) {
    val deck = deck.toMutableList()

    fun guess(higher: Boolean): Boolean {
        if (deck.size < 2) {
            throw GameEndedException()
        }
        val currentCard = deck[0]
        val nextCard = deck[1]
        deck.removeAt(0)
        return if (higher) {
            nextCard > currentCard
        } else {
            nextCard < currentCard
        }
    }

    class GameEndedException : RuntimeException()
}