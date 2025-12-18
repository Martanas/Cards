package com.martanhub.card

import kotlin.math.pow

open class HighLowGame(deck: List<PlayingCard>) {
    protected val deck = deck.toMutableList()
    private var score = 0
    open var correctAnswerStreak = 0

    fun score() = score
    fun guess(higher: Boolean): GameResult {
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
        updateScore(guessedCorrectly)
        return when {
            deck.size < 2 -> GameResult.Ended(guessedCorrectly)
            else -> GameResult.Ongoing(guessedCorrectly)
        }
    }

    private fun updateScore(guessedCorrectly: Boolean) {
        val regularScoreIncrement = 1
        if (guessedCorrectly) {
            val streakBonus = 2.0.pow(correctAnswerStreak - 1).toInt()
                .takeIf { correctAnswerStreak > 0 } ?: 0
            println(streakBonus)
            score += regularScoreIncrement + streakBonus
            correctAnswerStreak += 1
        } else {
            correctAnswerStreak = 0
        }
    }

    class GameEndedException : RuntimeException()

    sealed interface GameResult {
        val result: Boolean

        data class Ongoing(override val result: Boolean) : GameResult
        data class Ended(override val result: Boolean) : GameResult

        fun hasEnded() = this is Ended
    }
}