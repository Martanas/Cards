package com.martanhub.card

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class FrenchPlayingCardTest {
    @Test
    fun `ace beats king`() {
        assertTrue(FrenchRank.ACE > FrenchRank.KING)
    }

    @Test
    fun `creates standard French card deck`() {
        val expected = createStandardFrenchDeck()

        val actual = FrenchPlayingCard.createStandardDeck()

        assertEquals(expected, actual)
    }

    @Test
    fun `two same rank and suit cards are equal`() {
        val first = FrenchPlayingCard(FrenchRank.KING, FrenchSuit.HEARTS)
        val second = FrenchPlayingCard(FrenchRank.KING, FrenchSuit.HEARTS)

        assertEquals(first, second)
    }

    @Test
    fun `same rank different suit cards are not equal`() {
        val first = FrenchPlayingCard(FrenchRank.KING, FrenchSuit.HEARTS)
        val second = FrenchPlayingCard(FrenchRank.KING, FrenchSuit.SPADES)

        assertNotEquals(first, second)
        assertTrue(first < second)
    }

    @Test
    fun `different rank same suit cards are not equal`() {
        val first = FrenchPlayingCard(FrenchRank.KING, FrenchSuit.HEARTS)
        val second = FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.HEARTS)

        assertNotEquals(first, second)
        assertTrue(first < second)
    }

    @Test
    fun `different rank different suit cards are not equal`() {
        val first = FrenchPlayingCard(FrenchRank.KING, FrenchSuit.HEARTS)
        val second = FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.SPADES)

        assertNotEquals(first, second)
        assertTrue(first < second)
    }

    @Test
    fun `lowest rank is One`() {
        val expected = FrenchRank.ONE
        val actual = FrenchRank.Factory.lowest()

        assertEquals(expected, actual)
    }

    @Test
    fun `highest rank is Ace`() {
        val expected = FrenchRank.ACE
        val actual = FrenchRank.Factory.highest()

        assertEquals(expected, actual)
    }

    private fun createStandardFrenchDeck(): List<FrenchPlayingCard> {
        val expected = listOf(
            FrenchPlayingCard(FrenchRank.ONE, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.ONE, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.ONE, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.ONE, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.THREE, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.THREE, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.THREE, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.THREE, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.FOUR, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.FOUR, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.FOUR, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.FOUR, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.FIVE, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.FIVE, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.FIVE, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.FIVE, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.SIX, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.SIX, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.SIX, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.SIX, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.SEVEN, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.SEVEN, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.SEVEN, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.SEVEN, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.EIGHT, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.EIGHT, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.EIGHT, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.EIGHT, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.NINE, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.NINE, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.NINE, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.NINE, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.TEN, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.TEN, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TEN, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.TEN, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.JACK, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.JACK, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.JACK, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.JACK, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.QUEEN, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.QUEEN, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.QUEEN, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.QUEEN, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.KING, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.KING, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.KING, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.KING, FrenchSuit.SPADES),

            FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.SPADES),
        )
        return expected
    }
}