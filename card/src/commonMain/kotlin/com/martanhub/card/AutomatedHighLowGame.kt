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
        _gamePlayers.value.forEach { gamePlayer ->
            correctAnswerStreak = gamePlayer.streak
            play(gamePlayer)
        }
    }

    private suspend fun play(gamePlayer: GamePlayer) {
        val playerGuess = guess(gamePlayer.player.guess(deck[0]))
        val updatedPlayer = gamePlayer.copy(
            streak = correctAnswerStreak,
            score = score()
        )
        _gamePlayers.update { gamePlayers ->
            gamePlayers.map { gamePlayer ->
                if (gamePlayer.player.id == updatedPlayer.player.id) {
                    updatedPlayer
                } else {
                    gamePlayer
                }
            }
        }
        if (!playerGuess.hasEnded()) {
            play(updatedPlayer)
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