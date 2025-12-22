package com.martanhub.card

import kotlin.test.Test
import kotlin.test.assertEquals

class FrenchCardDeckTest {
    @Test
    fun `creates standard French card deck`() {
        val expected = createStandardFrenchDeck()

        val actual = FrenchCardDeck.create()

        assertEquals(expected, actual)
    }

    private fun createStandardFrenchDeck() = FrenchCardDeck(
        listOf(
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
    )
}