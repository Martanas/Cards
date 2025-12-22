package com.martanhub.card

import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.Test

class ShuffleMachineTest {
    @Test
    fun `at least one card changed it's position from the original deck`() {
        val machine = ShuffleMachine()

        val original = createTestDeck()
        val shuffled = machine.shuffle(original)

        val anyCardChangedPos = shuffled.cards.any { shuffledCard ->
            original.cards.indexOf(shuffledCard) != shuffled.cards.indexOf(shuffledCard)
        }
        assertTrue("No cards changed their position", anyCardChangedPos)
    }

    private fun createTestDeck() = TestDeck(
        cards = listOf(
            FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.THREE, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.FOUR, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.FIVE, FrenchSuit.SPADES),
            FrenchPlayingCard(FrenchRank.SIX, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.SEVEN, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.EIGHT, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.NINE, FrenchSuit.DIAMONDS),
            FrenchPlayingCard(FrenchRank.TEN, FrenchSuit.SPADES),
            FrenchPlayingCard(FrenchRank.JACK, FrenchSuit.SPADES),
            FrenchPlayingCard(FrenchRank.QUEEN, FrenchSuit.HEARTS),
            FrenchPlayingCard(FrenchRank.KING, FrenchSuit.CLUBS),
            FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.DIAMONDS),
        )
    )

    private data class TestDeck(override val cards: List<PlayingCard>) : Deck
}