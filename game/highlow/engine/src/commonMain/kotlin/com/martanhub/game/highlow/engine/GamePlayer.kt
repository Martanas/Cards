package com.martanhub.game.highlow.engine

import com.martanhub.card.core.PlayingCard

class GamePlayer(
    val id: Long,
    val player: Player,
    val score: Int,
    val streak: Int
) {
    internal suspend fun guess(playingCard: PlayingCard) = player.guess(playingCard)

    internal fun copy(streak: Int, score: Int) = GamePlayer(
        id = id,
        player = player,
        score = score,
        streak = streak
    )
}