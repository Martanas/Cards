package com.martanhub.card

data class FrenchPlayingCard(
    override val rank: FrenchRank,
    override val suit: FrenchSuit
) : PlayingCard {
    companion object {
        fun createStandardDeck(): List<PlayingCard> =
            FrenchRank.entries.map { rank ->
                FrenchSuit.entries.map { suit ->
                    FrenchPlayingCard(rank, suit)
                }
            }.flatten()
    }
}

enum class FrenchRank : Rank {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

enum class FrenchSuit : Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}