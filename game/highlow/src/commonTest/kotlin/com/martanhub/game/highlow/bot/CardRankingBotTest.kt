package com.martanhub.game.highlow.bot

import com.martanhub.card.FrenchPlayingCard
import com.martanhub.card.FrenchRank
import com.martanhub.card.FrenchSuit
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CardRankingBotTest {
    private val bot = CardRankingBot(FrenchRank.Factory)

    @Test
    fun `given one, guesses higher`() = runTest {
        assertTrue(bot.guess(FrenchPlayingCard(FrenchRank.ONE, FrenchSuit.DIAMONDS)))
    }

    @Test
    fun `given seven, guesses higher`() = runTest {
        assertTrue(bot.guess(FrenchPlayingCard(FrenchRank.SEVEN, FrenchSuit.DIAMONDS)))
    }

    @Test
    fun `given eight, guesses lower`() = runTest {
        assertFalse(bot.guess(FrenchPlayingCard(FrenchRank.EIGHT, FrenchSuit.DIAMONDS)))
    }

    @Test
    fun `given ace, guesses lower`() = runTest {
        assertFalse(bot.guess(FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.DIAMONDS)))
    }
}