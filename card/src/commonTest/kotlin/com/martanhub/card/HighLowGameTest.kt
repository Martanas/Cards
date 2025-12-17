package com.martanhub.card

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HighLowGameTest {
    @Test
    fun canStartGameWithCards() {
        val deck = FrenchPlayingCard.createStandardDeck()
        val game = HighLowGame(deck)
        game.start()
    }

    @Test
    fun correctGuessForNextCardReturnsTrue() {
        val game = HighLowGame(fakeSameSuitDeck())

        assertTrue(game.guess(false))
    }

    @Test
    fun wrongGuessForNextCardReturnsFalse() {
        val game = HighLowGame(fakeSameSuitDeck())

        assertFalse(game.guess(true))
    }

    private fun fakeSameSuitDeck() =
        FrenchPlayingCard.createStandardDeck()
            .filter { card -> card.suit === FrenchSuit.DIAMONDS }
}