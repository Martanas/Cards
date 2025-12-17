package com.martanhub.card

class HighLowGame(deck: List<PlayingCard>) {
    private val deck = deck.toMutableList()
    private var score = 0

    fun score() = score
    fun guess(higher: Boolean): Boolean {
        if (deck.size < 2) {
            throw GameEndedException()
        }
        val currentCard = deck[0]
        val nextCard = deck[1]
        deck.removeAt(0)
        val guessedCorrectly = if (higher) {
            nextCard > currentCard
        } else {
            nextCard < currentCard
        }
        if (guessedCorrectly) {
            score += 10
        }
        return guessedCorrectly
    }


    class GameEndedException : RuntimeException()
}