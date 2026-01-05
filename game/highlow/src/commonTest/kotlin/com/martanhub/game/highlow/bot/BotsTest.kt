package com.martanhub.game.highlow.bot

import com.martanhub.card.FrenchCardDeck
import com.martanhub.card.FrenchRank
import com.martanhub.card.ShuffleMachine
import com.martanhub.game.highlow.DefaultAutomatedHighLowGame
import com.martanhub.game.highlow.GamePlayer
import com.martanhub.game.highlow.Player
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class BotsTest {
    private val shuffleMachine = ShuffleMachine()

    @Test
    fun `random guessing bot averages less than current card ranking bot`() = runTest {
        val result = playTheGame(
            players = {
                listOf(RandomGuessingBot(), CurrentCardRankingBot(FrenchRank.Factory))
            },
            numOfTimes = 100
        )

        val randomGuessingBotAvg =
            result.first { (player, _) -> player is RandomGuessingBot }.second
        val currentCardRankingAvg =
            result.first { (player, _) -> player is CurrentCardRankingBot }.second
        assertTrue(
            message = "Current card ranking bot lost to random guessing bot",
            actual = randomGuessingBotAvg < currentCardRankingAvg
        )
    }

    @Test
    fun `last avg ranking bot averages less than current card ranking bot averages`() = runTest {
        val result = playTheGame(
            players = {
                listOf(
                    LastCardsAvgRankingBot(count = 5, rankFactory = FrenchRank.Factory),
                    CurrentCardRankingBot(FrenchRank.Factory)
                )
            },
            numOfTimes = 100
        )

        val lastCardsRankingBotAvg =
            result.first { (player, _) -> player is LastCardsAvgRankingBot }.second
        val currentCardRankingAvg =
            result.first { (player, _) -> player is CurrentCardRankingBot }.second
        assertTrue(
            message = "Last avg card ranking bot current card ranking bot",
            actual = lastCardsRankingBotAvg < currentCardRankingAvg
        )
    }

    private suspend fun playTheGame(
        players: () -> List<Player>,
        numOfTimes: Int
    ): List<Pair<Player, Int>> {
        var playersWithScores = players().map { player -> player to 0 }
        repeat(numOfTimes) {
            val shuffledDeck = shuffleMachine.shuffle(FrenchCardDeck.create())
            val highLowGame = DefaultAutomatedHighLowGame(
                players = players(),
                deck = shuffledDeck
            )
            highLowGame.start()
            playersWithScores = playersWithScores.map { (player, score) ->
                player to score + highLowGame.findPlayer(player).score
            }
        }

        return playersWithScores.map { (player, score) -> player to score.div(numOfTimes) }
    }

    private fun DefaultAutomatedHighLowGame.findPlayer(
        player: Player
    ): GamePlayer = gamePlayers.value.first { it.player.name == player.name }
}