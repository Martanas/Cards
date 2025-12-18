package com.martanhub.card

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AutomatedHighLowGame(
    players: List<Player>,
    deck: List<PlayingCard>
) : HighLowGame(deck) {
    private val _gamePlayers = MutableStateFlow(
        players.map { player ->
            GamePlayer(player = player, score = 0, streak = 0)
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
        val guessResult = guess(gamePlayer.player.guess(deck[0]))
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
            .filter { it.player.id != gamePlayer.player.id }
            .sumOf { it.score }
        return totalGameScore - otherPlayersScore
    }

    private fun List<GamePlayer>.updatePlayer(gamePlayerToUpdate: GamePlayer) = map { gamePlayer ->
        if (gamePlayer.player.id == gamePlayerToUpdate.player.id) {
            gamePlayerToUpdate
        } else {
            gamePlayer
        }
    }

    data class GamePlayer(
        val player: Player,
        val score: Int,
        val streak: Int
    )
}

interface Player {
    val id: Long
    suspend fun guess(card: PlayingCard): Boolean
}