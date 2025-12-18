package com.martanhub.game.highlow

import kotlinx.coroutines.flow.StateFlow

interface AutomatedHighLowGame {
    val gamePlayers: StateFlow<List<GamePlayer>>
    suspend fun start()
}