package com.martanhub.card

data class FrenchPlayingCard(
    override val rank: FrenchRank,
    override val suit: FrenchSuit
) : PlayingCard {
    override fun compareTo(other: PlayingCard): Int {
        require(other is FrenchPlayingCard)
        return when {
            this.rank < other.rank -> -1
            this.rank > other.rank -> 1
            else -> when {
                this.suit < other.suit -> -1
                this.suit > other.suit -> 1
                else -> 0
            }
        }
    }

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
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    override val value: Int = this.ordinal

    override fun compareTo(other: Rank): Int {
        require(other is FrenchRank)
        return when {
            this.ordinal < other.ordinal -> -1
            this.ordinal > other.ordinal -> 1
            else -> 0
        }
    }

    object Factory : Rank.Factory {
        override fun lowest(): Rank = ONE

        override fun highest(): Rank = ACE
    }
}

enum class FrenchSuit : Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES;

    override fun compareTo(other: Suit): Int {
        require(other is FrenchSuit)
        return when {
            this.ordinal < other.ordinal -> -1
            this.ordinal > other.ordinal -> 1
            else -> 0
        }
    }
}