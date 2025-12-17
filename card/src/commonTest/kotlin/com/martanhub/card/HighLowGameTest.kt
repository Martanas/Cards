package com.martanhub.card

import com.martanhub.card.HighLowGame.GameEndedException
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HighLowGameTest {
    @Test
    fun correctGuessForNextCardReturnsTrue() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertTrue(game.guess(true))
    }

    @Test
    fun wrongGuessForNextCardReturnsFalse() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertFalse(game.guess(false))
    }

    @Test
    fun canPerformTwoCorrectGuessesInARow() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        assertTrue(game.guess(true))
        assertTrue(game.guess(false))
    }

    @Test
    fun guessForHigherCardOnASameCardReturnsFalse() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        )
        val game = HighLowGame(deck)

        assertFalse(game.guess(true))
    }

    @Test
    fun guessForLowerCardOnASameCardReturnsFalse() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        )
        val game = HighLowGame(deck)

        assertFalse(game.guess(false))
    }

    @Test
    fun guessAfterRunningOutOfCardsThrowsException() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        )
        val game = HighLowGame(deck)

        game.guess(true)
        assertFailsWith(GameEndedException::class) { game.guess(true) }
    }

    private fun createFakeSameSuitOrderedDeck() =
        FrenchPlayingCard.createStandardDeck()
            .filter { card -> card.suit === FrenchSuit.DIAMONDS }

    private fun createFakeSameSuitUnOrderedDeck() = listOf(
        FrenchPlayingCard(FrenchRank.ONE, FrenchSuit.DIAMONDS),
        FrenchPlayingCard(FrenchRank.FOUR, FrenchSuit.DIAMONDS),
        FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
    )
}