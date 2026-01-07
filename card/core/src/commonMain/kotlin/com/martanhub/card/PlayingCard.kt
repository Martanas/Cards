package com.martanhub.card

interface PlayingCard : Comparable<PlayingCard> {
    val rank: Rank
    val suit: Suit
}

interface Rank : Comparable<Rank> {
    val value: Int

    interface Factory {
        fun lowest(): Rank

        fun highest(): Rank
    }
}

interface Suit