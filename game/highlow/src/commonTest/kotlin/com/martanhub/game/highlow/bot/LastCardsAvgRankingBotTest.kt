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
        assertTrue(bot.guess(playingCard(FrenchRank.TWO)))
    }

    @Test
    fun `given seven, guesses higher`() = runTest {
        assertTrue(bot.guess(playingCard(FrenchRank.SEVEN)))
    }

    @Test
    fun `given seven after ace, two and five guesses higher`() = runTest {
        bot.guess(playingCard(FrenchRank.ACE))
        bot.guess(playingCard(FrenchRank.TWO))
        bot.guess(playingCard(FrenchRank.FIVE))

        assertTrue(bot.guess(playingCard(FrenchRank.SEVEN)))
    }

    @Test
    fun `given seven after ace, five and five guesses higher`() = runTest {
        bot.guess(playingCard(FrenchRank.ACE))
        bot.guess(playingCard(FrenchRank.FIVE))
        bot.guess(playingCard(FrenchRank.FIVE))

        assertTrue(bot.guess(playingCard(FrenchRank.SEVEN)))
    }

    @Test
    fun `given eight guesses lower`() = runTest {
        assertFalse(bot.guess(playingCard(FrenchRank.EIGHT)))
    }

    @Test
    fun `given eight after jack, two and six, guesses higher`() = runTest {
        bot.guess(playingCard(FrenchRank.JACK))
        bot.guess(playingCard(FrenchRank.TWO))
        bot.guess(playingCard(FrenchRank.SIX))

        assertTrue(bot.guess(playingCard(FrenchRank.EIGHT)))
    }

    @Test
    fun `given ace guesses lower`() = runTest {
        assertFalse(bot.guess(playingCard(FrenchRank.ACE)))
    }

    @Test
    fun `given ace after king and seven guesses lower`() = runTest {
        bot.guess(playingCard(FrenchRank.ACE))
        bot.guess(playingCard(FrenchRank.KING))

        assertFalse(bot.guess(playingCard(FrenchRank.SEVEN)))
    }

    @Test
    fun `given eight after two and six eight guesses higher`() = runTest {
        bot.guess(playingCard(FrenchRank.TWO))
        bot.guess(playingCard(FrenchRank.SIX))

        assertTrue(bot.guess(playingCard(FrenchRank.EIGHT)))
    }

    @Test
    fun `given six after ace guesses lower`() = runTest {
        bot.guess(playingCard(FrenchRank.ACE))

        assertFalse(bot.guess(playingCard(FrenchRank.SIX)))
    }

    @Test
    fun `given king after two guesses lower`() = runTest {
        bot.guess(playingCard(FrenchRank.TWO))

        assertFalse(bot.guess(playingCard(FrenchRank.KING)))
    }

    private fun playingCard(rank: FrenchRank) = FrenchPlayingCard(
        rank = rank,
        suit = FrenchSuit.DIAMONDS
    )
}