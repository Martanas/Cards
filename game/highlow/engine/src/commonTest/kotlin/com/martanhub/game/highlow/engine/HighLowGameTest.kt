package com.martanhub.game.highlow.engine

import com.martanhub.card.core.FrenchCardDeck
import com.martanhub.card.core.FrenchPlayingCard
import com.martanhub.card.core.FrenchRank
import com.martanhub.card.core.FrenchSuit
import com.martanhub.game.highlow.engine.HighLowGame.GameEndedException
import com.martanhub.game.highlow.engine.TestShuffledDeck.Companion.toTestShuffledDeck
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HighLowGameTest {
    @Test
    fun `correct guess for next card returns true`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertTrue(game.guess(true).correct)
    }

    @Test
    fun `incorrect guess for next card returns false`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        assertFalse(game.guess(false).correct)
    }

    @Test
    fun `can perform two correct guesses in a row`() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        assertTrue(game.guess(true).correct)
        assertTrue(game.guess(false).correct)
    }

    @Test
    fun `guess for higher card on a same card returns false`() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        ).toTestShuffledDeck()
        val game = HighLowGame(deck)

        assertFalse(game.guess(true).correct)
    }

    @Test
    fun `guess for lower card on a same card returns false`() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        ).toTestShuffledDeck()
        val game = HighLowGame(deck)

        assertFalse(game.guess(false).correct)
    }

    @Test
    fun `guess after running out of cards throws exception`() {
        val deck = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        ).toTestShuffledDeck()
        val game = HighLowGame(deck)

        game.guess(true)
        assertFailsWith(GameEndedException::class) { game.guess(true) }
    }

    @Test
    fun `incorrect guess does not affect score`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        game.assertState(score = 0, streak = 0)

        game.guess(false)
        game.assertState(score = 0, streak = 0)
    }

    @Test
    fun `correct guess affects score`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        game.assertState(score = 0, streak = 0)

        game.guess(true)
        game.assertState(score = 1, streak = 1)
    }

    @Test
    fun `correct guesses not in a row affects score regularly`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        game.assertState(score = 0, streak = 0)

        game.guess(true)
        game.assertState(score = 1, streak = 1)

        game.guess(false)
        game.assertState(score = 1, streak = 0)

        game.guess(true)
        game.assertState(score = 2, streak = 1)
    }

    @Test
    fun `correct guesses in a row affects score in a geometric progression`() {
        val game = HighLowGame(createFakeSameSuitOrderedDeck())

        game.assertState(score = 0, streak = 0)

        game.guess(true)
        game.assertState(score = 1, streak = 1)

        game.guess(true)
        game.assertState(score = 3, streak = 2)

        game.guess(true)
        game.assertState(score = 6, streak = 3)

        game.guess(true)
        game.assertState(score = 11, streak = 4)
    }

    @Test
    fun `game is not ended when there are cards left to play`() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        assertFalse(game.guess(false).hasGameEnded())
        assertFalse(game.hasEnded())
    }

    @Test
    fun `game is ended when there are no cards left to play`() {
        val game = HighLowGame(createFakeSameSuitUnOrderedDeck())

        assertFalse(game.hasEnded())

        game.guess(true)
        assertTrue(game.guess(false).hasGameEnded())
        assertTrue(game.hasEnded())
    }

    private fun createFakeSameSuitOrderedDeck() =
        FrenchCardDeck.create()
            .cards
            .filter { card -> card.suit === FrenchSuit.DIAMONDS }
            .toTestShuffledDeck()

    private fun createFakeSameSuitUnOrderedDeck() = listOf(
        FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
        FrenchPlayingCard(FrenchRank.FOUR, FrenchSuit.DIAMONDS),
        FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
    ).toTestShuffledDeck()

    private fun HighLowGame.assertState(score: Int, streak: Int) {
        assertEquals("Score is incorrect", score, score())
        assertEquals("Streak is incorrect", streak, streak())
    }
}