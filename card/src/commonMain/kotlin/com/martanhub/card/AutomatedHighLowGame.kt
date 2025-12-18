package com.martanhub.card

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.concurrent.atomics.AtomicLong
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.incrementAndFetch

@OptIn(ExperimentalAtomicApi::class)
class AutomatedHighLowGame(
    players: List<Player>,
    deck: List<PlayingCard>
) : HighLowGame(deck) {
    private val nextPlayerId = AtomicLong(0)
    private val _gamePlayers = MutableStateFlow(
        players.map { player ->
            GamePlayer(
                id = nextPlayerId.incrementAndFetch(),
                player = player,
                score = 0,
                streak = 0
            )
        }
    )
    val gamePlayers = _gamePlayers.asStateFlow()

    suspend fun start() {
        while (!hasEnded()) {
            _gamePlayers.value.forEach { gamePlayer ->
                if (!hasEnded()) {
                    play(gamePlayer)
                }
            }
        }
    }

    private suspend fun play(gamePlayer: GamePlayer) {
        val guessResult = guess(gamePlayer.guess(deck[0]))
        val updatedPlayer = updatePlayerState(gamePlayer)
        if (!guessResult.hasGameEnded() && guessResult.correct) {
            play(updatedPlayer)
        }
    }

    private fun updatePlayerState(gamePlayer: GamePlayer): GamePlayer {
        val updatedPlayer = gamePlayer.copy(
            streak = streak(),
            score = scoreForPlayer(gamePlayer)
        )
        _gamePlayers.update { gamePlayers -> gamePlayers.updatePlayer(updatedPlayer) }
        return updatedPlayer
    }

    private fun scoreForPlayer(gamePlayer: GamePlayer): Int {
        val totalGameScore = score()
        val otherPlayersScore = _gamePlayers.value
            .filter { it.id != gamePlayer.id }
            .sumOf { it.score }
        return totalGameScore - otherPlayersScore
    }

    private fun List<GamePlayer>.updatePlayer(gamePlayerToUpdate: GamePlayer) = map { gamePlayer ->
        if (gamePlayer.id == gamePlayerToUpdate.id) {
            gamePlayerToUpdate
        } else {
            gamePlayer
        }
    }

    class GamePlayer(
        val id: Long,
        val player: Player,
        val score: Int,
        val streak: Int
    ) {
        suspend fun guess(playingCard: PlayingCard) = player.guess(playingCard)

        fun copy(streak: Int, score: Int) = GamePlayer(
            id = id,
            player = player,
            score = score,
            streak = streak
        )
    }
}

interface Player {
    val name: String
    suspend fun guess(card: PlayingCard): Boolean
}