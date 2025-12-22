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
    fun `lowest rank is Two`() {
        val expected = FrenchRank.TWO
        val actual = FrenchRank.Factory.lowest()

        assertEquals(expected, actual)
    }

    @Test
    fun `highest rank is Ace`() {
        val expected = FrenchRank.ACE
        val actual = FrenchRank.Factory.highest()

        assertEquals(expected, actual)
    }
}