package com.martanhub.game.highlow.bot

import com.martanhub.card.FrenchPlayingCard
import com.martanhub.card.FrenchRank
import com.martanhub.card.FrenchSuit
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LastCardsAvgRankingBotTest {
    private val bot = LastCardsAvgRankingBot(FrenchRank.Factory, 3)

    @Test
    fun `given two, guesses higher`() = runTest {
        assertTrue(bot.guess(FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS)))
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

    @Test
    fun `given ace, king and seven guesses lower`() = runTest {
        bot.guess(FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.DIAMONDS))
        bot.guess(FrenchPlayingCard(FrenchRank.KING, FrenchSuit.DIAMONDS))

        assertFalse(bot.guess(FrenchPlayingCard(FrenchRank.SEVEN, FrenchSuit.DIAMONDS)))
    }

    @Test
    fun `given two, six and eight guesses higher`() = runTest {
        bot.guess(FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS))
        bot.guess(FrenchPlayingCard(FrenchRank.SIX, FrenchSuit.DIAMONDS))

        assertTrue(bot.guess(FrenchPlayingCard(FrenchRank.EIGHT, FrenchSuit.DIAMONDS)))
    }

    @Test
    fun `given ace and six guesses lower`() = runTest {
        bot.guess(FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.DIAMONDS))

        assertFalse(bot.guess(FrenchPlayingCard(FrenchRank.SIX, FrenchSuit.DIAMONDS)))
    }

    @Test
    fun `given two and king guesses lower`() = runTest {
        bot.guess(FrenchPlayingCard(FrenchRank.TWO, FrenchSuit.DIAMONDS))

        assertFalse(bot.guess(FrenchPlayingCard(FrenchRank.KING, FrenchSuit.DIAMONDS)))
    }
}