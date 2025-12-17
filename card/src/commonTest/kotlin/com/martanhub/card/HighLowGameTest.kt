package com.martanhub.card

import com.martanhub.card.HighLowGame.GameEndedException
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HighLowGameTest {
    @Test
    fun `correct guess for next card returns true`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertTrue(game.guess(true))
    }

    @Test
    fun `wrong guess for next card returns false`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertFalse(game.guess(false))
    }

    @Test
    fun `can perform two correct guesses in a row`() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        assertTrue(game.guess(true))
        assertTrue(game.guess(false))
    }

    @Test
    fun `guess for higher card on a same card returns false`() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        )
        val game = HighLowGame(deck)

        assertFalse(game.guess(true))
    }

    @Test
    fun `guess for lower card on a same card returns false`() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        )
        val game = HighLowGame(deck)

        assertFalse(game.guess(false))
    }

    @Test
    fun `guess after running out of cards throws exception`() {
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