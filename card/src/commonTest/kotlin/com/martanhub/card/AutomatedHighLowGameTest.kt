package com.martanhub.card

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AutomatedHighLowGameTest {
    private val firstPlayerGuesses = MutableSharedFlow<Boolean>()

    @Test
    fun `start calls player to guess`() = runTest {
        val player = TestPlayer(firstPlayerGuesses)
        val game = AutomatedHighLowGame(
            players = listOf(player),
            deck = createFakeSameSuitUnOrderedDeck(take = 2)
        )
        launch(Dispatchers.Unconfined) {
            game.start()
        }
        firstPlayerGuesses.emit(false)
        assertTrue(player.guessCalled)
    }

    @Test
    fun `game ends with no exception`() = runTest {
        val player = TestPlayer(firstPlayerGuesses)
        val game = AutomatedHighLowGame(
            players = listOf(player),
            deck = createFakeSameSuitUnOrderedDeck(take = 2)
        )
        launch(Dispatchers.Unconfined) {
            game.start()
        }
        firstPlayerGuesses.emit(true)
        firstPlayerGuesses.emit(true)
    }

    @Test
    fun `game updates players score and streak`() = runTest {
        val player = TestPlayer(firstPlayerGuesses)
        val game = AutomatedHighLowGame(
            players = listOf(player),
            deck = createFakeSameSuitUnOrderedDeck(take = 2)
        )
        launch(Dispatchers.Unconfined) {
            game.start()
        }
        assertEquals(0, game.gamePlayers.value.first().score)
        assertEquals(0, game.gamePlayers.value.first().streak)

        firstPlayerGuesses.emit(true)
        assertEquals(1, game.gamePlayers.value.first().score)
        assertEquals(1, game.gamePlayers.value.first().streak)
    }

    private class TestPlayer(private val guessStream: SharedFlow<Boolean>) : Player {
        var guessCalled = false
        override val id: Long = 69L

        override suspend fun guess(card: PlayingCard): Boolean {
            guessCalled = true
            return guessStream.first()
        }
    }

    private fun createFakeSameSuitUnOrderedDeck(take: Int = Int.MAX_VALUE) =
        FrenchPlayingCard.createStandardDeck()
            .filter { card -> card.suit === FrenchSuit.DIAMONDS }
            .take(take)
}