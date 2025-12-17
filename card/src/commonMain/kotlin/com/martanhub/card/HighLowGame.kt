package com.martanhub.card

class HighLowGame(deck: List<PlayingCard>) {
    val deck = deck.toMutableList()
    fun start() {

    }

    fun guess(higher: Boolean): Boolean {
        val currentCard = deck[0]
        val nextCard = deck[1]
        return if (higher) {
            nextCard.rank > currentCard.rank
        } else {
            nextCard.rank < currentCard.rank
        }
    }
}