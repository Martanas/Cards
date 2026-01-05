package com.martanhub.game.highlow.bot

import com.martanhub.card.PlayingCard
import com.martanhub.game.highlow.Player
import kotlin.random.Random

class RandomGuessingBot(
    private val random: () -> Boolean = { Random.nextBoolean() }
) : Player {
    override val name = "Bot: Random Guesser"

    override suspend fun guess(card: PlayingCard): Boolean = random()

    override fun spectate(card: PlayingCard) {
        // Do nothing
    }
}