package com.martanhub.game.highlow.bot

import com.martanhub.card.FrenchPlayingCard
import com.martanhub.card.FrenchRank
import com.martanhub.card.FrenchSuit
import kotlinx.coroutines.test.runTest
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.Test

class RandomGuessingBotTest {
    @Test
    fun `bot has a name`() {
        val bot = RandomGuessingBot()

        assertEquals("Name is incorrect", "Bot: Random Guesser", bot.name)
    }

    @Test
    fun `bot guesses according to random provider`() = runTest {
        var guess = false
        val card = FrenchPlayingCard(FrenchRank.ACE, FrenchSuit.SPADES)
        val bot = RandomGuessingBot { guess }

        assertEquals("Bot did not guess according to random", guess, bot.guess(card))

        guess = true
        assertEquals("Bot did not guess according to random", guess, bot.guess(card))
    }
}