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
    private val secondPlayerGuesses = MutableSharedFlow<Boolean>()

    @Test
    fun `start calls player to guess`() = runTest {
        val player = TestPlayer(id = 1L, guessStream = firstPlayerGuesses)
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
        val player = TestPlayer(id = 1L, guessStream = firstPlayerGuesses)
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
        val player = TestPlayer(id = 1L, guessStream = firstPlayerGuesses)
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

    @Test
    fun `two players can play the game`() = runTest {
        val firstPlayer = TestPlayer(id = 1L, guessStream = firstPlayerGuesses)
        val secondPlayer = TestPlayer(id = 2L, guessStream = secondPlayerGuesses)
        val game = AutomatedHighLowGame(
            players = listOf(firstPlayer, secondPlayer),
            deck = createFakeSameSuitUnOrderedDeck(take = 7)
        )
        launch(Dispatchers.Unconfined) {
            game.start()
        }
        firstPlayerGuesses.emit(true)
        assertEquals(1, game.gamePlayers.value.first().score)
        assertEquals(1, game.gamePlayers.value.first().streak)
        firstPlayerGuesses.emit(false)
        assertEquals(1, game.gamePlayers.value.first().score)
        assertEquals(0, game.gamePlayers.value.first().streak)
        secondPlayerGuesses.emit(true)
        assertEquals(1, game.gamePlayers.value.last().score)
        assertEquals(1, game.gamePlayers.value.last().streak)
        secondPlayerGuesses.emit(true)
        assertEquals(3, game.gamePlayers.value.last().score)
        assertEquals(2, game.gamePlayers.value.last().streak)
        secondPlayerGuesses.emit(false)
        assertEquals(3, game.gamePlayers.value.last().score)
        assertEquals(0, game.gamePlayers.value.last().streak)
        firstPlayerGuesses.emit(true)
        assertEquals(2, game.gamePlayers.value.first().score)
        assertEquals(1, game.gamePlayers.value.first().streak)
    }

    private class TestPlayer(
        override val id: Long,
        private val guessStream: SharedFlow<Boolean>
    ) : Player {
        var guessCalled = false

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