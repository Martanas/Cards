package com.martanhub.game.highlow.engine.bot

import com.martanhub.card.core.PlayingCard
import com.martanhub.game.highlow.engine.Player
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