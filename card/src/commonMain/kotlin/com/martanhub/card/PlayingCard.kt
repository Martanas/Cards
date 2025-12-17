package com.martanhub.card

interface PlayingCard : Comparable<PlayingCard> {
    val rank: Rank
    val suit: Suit
}

interface Rank : Comparable<Rank>

interface Suit : Comparable<Suit>