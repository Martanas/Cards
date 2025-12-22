package com.martanhub.game.highlow

import com.martanhub.card.FrenchCardDeck
import com.martanhub.card.FrenchSuit
import com.martanhub.card.PlayingCard
import com.martanhub.game.highlow.TestShuffledDeck.Companion.toTestShuffledDeck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.Test

class DefaultAutomatedHighLowGameTest {
    @Test
    fun `game ends with no exception`() = runTest {
        val player = TestPlayer(name = "Player #69")
        val game = DefaultAutomatedHighLowGame(
            players = listOf(player),
            deck = createFakeSameSuitUnOrderedDeck(take = 2)
        )
        launch(Dispatchers.Unconfined) {
            game.start()
        }
        player.guessHigher()
        player.guessHigher()
    }

    @Test
    fun `game updates players score and streak`() = runTest {
        val player = TestPlayer(name = "Player #420")
        val game = DefaultAutomatedHighLowGame(
            players = listOf(player),
            deck = createFakeSameSuitUnOrderedDeck(take = 2)
        )
        launch(Dispatchers.Unconfined) {
            game.start()
        }

        game.assertPlayerState(player, score = 0, streak = 0)

        player.guessHigher()
        game.assertPlayerState(player, score = 1, streak = 1)
    }

    @Test
    fun `two players can play the game`() = runTest {
        val firstPlayer = TestPlayer(name = "Player #112")
        val secondPlayer = TestPlayer(name = "Player #911")
        val game = DefaultAutomatedHighLowGame(
            players = listOf(firstPlayer, secondPlayer),
            deck = createFakeSameSuitUnOrderedDeck(take = 7)
        )
        launch(Dispatchers.Unconfined) {
            game.start()
        }

        firstPlayer.guessHigher()
        game.assertPlayerState(firstPlayer, score = 1, streak = 1)

        firstPlayer.guessLower()
        game.assertPlayerState(firstPlayer, score = 1, streak = 0)

        secondPlayer.guessHigher()
        game.assertPlayerState(secondPlayer, score = 1, streak = 1)

        secondPlayer.guessHigher()
        game.assertPlayerState(secondPlayer, score = 3, streak = 2)

        secondPlayer.guessLower()
        game.assertPlayerState(secondPlayer, score = 3, streak = 0)

        firstPlayer.guessHigher()
        game.assertPlayerState(firstPlayer, score = 2, streak = 1)
    }

    private fun DefaultAutomatedHighLowGame.assertPlayerState(
        player: Player,
        score: Int,
        streak: Int
    ) {
        val gamePlayer = gamePlayers.value.find { it.player.name == player.name }
        assertEquals("Score for ${player.name} was incorrect", score, gamePlayer?.score)
        assertEquals("Streak for ${player.name} was incorrect", streak, gamePlayer?.streak)
    }

    private class TestPlayer(
        override val name: String,
    ) : Player {
        private val guessStream = MutableSharedFlow<Boolean>()

        override suspend fun guess(card: PlayingCard): Boolean = guessStream.first()

        suspend fun guessHigher() = guessStream.emit(true)

        suspend fun guessLower() = guessStream.emit(false)
    }

    private fun createFakeSameSuitUnOrderedDeck(take: Int = Int.MAX_VALUE) =
        FrenchCardDeck.create()
            .cards
            .filter { card -> card.suit === FrenchSuit.DIAMONDS }
            .take(take)
            .toTestShuffledDeck()
}