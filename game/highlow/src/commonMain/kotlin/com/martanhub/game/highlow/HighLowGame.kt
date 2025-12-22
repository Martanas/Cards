package com.martanhub.game.highlow

import com.martanhub.card.Deck
import kotlin.math.pow

internal open class HighLowGame(deck: Deck) {
    protected val cards = deck.cards.toMutableList()
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
        val currentCard = cards[0]
        val nextCard = cards[1]
        cards.removeAt(0)
        val guessedCorrectly = if (higher) {
            nextCard > currentCard
        } else {
            nextCard < currentCard
        }
        updateScore(guessedCorrectly)
        val result = when {
            cards.size < 2 -> GuessResult.GameEnded(guessedCorrectly)
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