package com.martanhub.card

import com.martanhub.card.HighLowGame.GameEndedException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HighLowGameTest {
    @Test
    fun `correct guess for next card returns true`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertTrue(game.guess(true).result)
    }

    @Test
    fun `wrong guess for next card returns false`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertFalse(game.guess(false).result)
    }

    @Test
    fun `can perform two correct guesses in a row`() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        assertTrue(game.guess(true).result)
        assertTrue(game.guess(false).result)
    }

    @Test
    fun `guess for higher card on a same card returns false`() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        )
        val game = HighLowGame(deck)

        assertFalse(game.guess(true).result)
    }

    @Test
    fun `guess for lower card on a same card returns false`() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        )
        val game = HighLowGame(deck)

        assertFalse(game.guess(false).result)
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

    @Test
    fun `incorrect guess does not affect score`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertEquals(0, game.score())
        game.guess(false)
        assertEquals(0, game.score())
    }

    @Test
    fun `correct guess affects score`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertEquals(0, game.score())
        game.guess(true)
        assertEquals(1, game.score())
    }

    @Test
    fun `correct guesses not in a row affects score regularly`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertEquals(0, game.score())
        game.guess(true)
        assertEquals(1, game.score())
        game.guess(false)
        assertEquals(1, game.score())
        game.guess(true)
        assertEquals(2, game.score())
    }

    @Test
    fun `correct guesses in a row affects score in a geometric progression`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertEquals(0, game.score())
        game.guess(true)
        assertEquals(1, game.score())
        game.guess(true)
        assertEquals(3, game.score())
        game.guess(true)
        assertEquals(6, game.score())
        game.guess(true)
        assertEquals(11, game.score())
    }

    @Test
    fun `result is not ended when there are cards left to play`() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        assertFalse(game.guess(false).hasEnded())
    }

    @Test
    fun `result is ended when there are no cards left to play`() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        game.guess(true).result
        assertTrue(game.guess(false).hasEnded())
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