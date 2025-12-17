package com.martanhub.card

interface PlayingCard {
    val rank: Rank
    val suit: Suit
}

interface Rank : Comparable<Rank>

interface Suit