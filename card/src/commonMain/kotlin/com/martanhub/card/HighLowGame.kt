package com.martanhub.card

import kotlin.math.pow

open class HighLowGame(deck: List<PlayingCard>) {
    protected val deck = deck.toMutableList()
    private var score = 0
    private var correctAnswerStreak = 0
    private var lastResult: GuessResult? = null

    fun score() = score

    fun streak() = correctAnswerStreak

    fun hasEnded(): Boolean = lastResult?.hasGameEnded() == true

    fun guess(higher: Boolean): GuessResult {
        if (hasEnded()) {
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
        val result = when {
            deck.size < 2 -> GuessResult.GameEnded(guessedCorrectly)
            else -> GuessResult.GameContinues(guessedCorrectly)
        }
        lastResult = result
        return result
    }

    private fun updateScore(guessedCorrectly: Boolean) {
        val regularScoreIncrement = 1
        if (guessedCorrectly) {
            val streakBonus = 2.0.pow(correctAnswerStreak - 1).toInt()
                .takeIf { correctAnswerStreak > 0 } ?: 0
            score += regularScoreIncrement + streakBonus
            correctAnswerStreak += 1
        } else {
            correctAnswerStreak = 0
        }
    }

    class GameEndedException : RuntimeException()

    sealed interface GuessResult {
        val correct: Boolean

        data class GameContinues(override val correct: Boolean) : GuessResult
        data class GameEnded(override val correct: Boolean) : GuessResult

        fun hasGameEnded() = this is GameEnded
    }
}