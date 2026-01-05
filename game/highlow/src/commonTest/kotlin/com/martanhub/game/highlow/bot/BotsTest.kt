package com.martanhub.game.highlow.bot

import com.martanhub.card.FrenchCardDeck
import com.martanhub.card.FrenchPlayingCard
import com.martanhub.card.FrenchRank
import com.martanhub.card.FrenchSuit
import com.martanhub.card.ShuffleMachine
import com.martanhub.card.ShuffledDeck
import com.martanhub.game.highlow.DefaultAutomatedHighLowGame
import com.martanhub.game.highlow.Player
import com.martanhub.game.highlow.TestShuffledDeck
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class BotsTest {
    private val shuffleMachine = ShuffleMachine()

    @Test
    fun `current card ranking bot wins more often than random guessing bot`() = runTest {
        val result = playTheGame(
            players = {
                listOf(RandomGuessingBot(), CurrentCardRankingBot(FrenchRank.Factory))
            },
            numOfTimes = 1000
        )

        val randomGuessingBotWinCount =
            result.first { (player, _) -> player is RandomGuessingBot }.wins
        val currentCardRankingWinCount =
            result.first { (player, _) -> player is CurrentCardRankingBot }.wins
        assertTrue(
            message = "Current card ranking bot won less times than Random guessing bot",
            actual = currentCardRankingWinCount > randomGuessingBotWinCount
        )
    }

    @Test
    fun `current card ranking bot wins more often than last cards comparing bot`() = runTest {
        val result = playTheGame(
            players = {
                listOf(
                    LastCardsAvgRankingBot(count = 5, rankFactory = FrenchRank.Factory),
                    CurrentCardRankingBot(FrenchRank.Factory)
                )
            },
            numOfTimes = 1000
        )

        val lastCardsRankingBotWinCount =
            result.first { (player, _) -> player is LastCardsAvgRankingBot }.wins
        val currentCardRankingWinCount =
            result.first { (player, _) -> player is CurrentCardRankingBot }.wins
        assertTrue(
            message = "Current card ranking bot won less times than last cards comparing bot",
            actual = currentCardRankingWinCount > lastCardsRankingBotWinCount
        )
    }

    @Test
    fun `remaining card ranking bot wins more often than current card ranking bot`() = runTest {
        val result = playTheGame(
            players = { deck ->
                listOf(
                    RemainingCardsRankingBot(deck),
                    CurrentCardRankingBot(FrenchRank.Factory)
                )
            },
            numOfTimes = 100
        )

        val remainingCardsRankingBotWins =
            result.first { (player, _) -> player is RemainingCardsRankingBot }.wins
        val currentCardRankingWins =
            result.first { (player, _) -> player is CurrentCardRankingBot }.wins
        assertTrue(
            message = "Remaining cards ranking bot won less times than current card ranking bot",
            actual = remainingCardsRankingBotWins > currentCardRankingWins
        )
    }

    private suspend fun playTheGame(
        players: (ShuffledDeck) -> List<Player>,
        numOfTimes: Int
    ): List<PlayerGameResult> {
        var playersWithWins = players(
            TestShuffledDeck(
                listOf(
                    FrenchPlayingCard(
                        FrenchRank.ACE,
                        FrenchSuit.DIAMONDS
                    )
                )
            )
        ).map { player -> PlayerGameResult(player, 0) }
        val unshuffledDeck = FrenchCardDeck.create()
        repeat(numOfTimes) {
            val shuffledDeck = shuffleMachine.shuffle(unshuffledDeck)
            val highLowGame = DefaultAutomatedHighLowGame(
                players = players(shuffledDeck),
                deck = shuffledDeck
            )
            highLowGame.start()
            val winner = highLowGame.gamePlayers.value.maxBy { it.score }
            playersWithWins = playersWithWins.map { result ->
                result.copy(wins = if (winner.player.name == result.player.name) result.wins + 1 else result.wins)
            }
        }
        return playersWithWins
    }

    private data class PlayerGameResult(
        val player: Player,
        val wins: Int
    )
}