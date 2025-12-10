package com.martanhub.card

data class FrenchPlayingCard(
    override val rank: FrenchRank,
    override val suit: FrenchSuit
) : PlayingCard {
    companion object {
        fun createStandardDeck(): List<PlayingCard> {
            val deck = mutableListOf<FrenchPlayingCard>()
            FrenchRank.entries.forEach { rank ->
                FrenchSuit.entries.forEach { suit ->
                    deck.add(FrenchPlayingCard(rank, suit))
                }
            }
            return deck.toList()
        }
    }
}

enum class FrenchRank : Rank {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

enum class FrenchSuit : Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}