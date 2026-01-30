package com.martanhub.card.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import cards.card.ui.generated.resources.Res
import cards.card.ui.generated.resources.ic_clubs
import cards.card.ui.generated.resources.ic_diamonds
import cards.card.ui.generated.resources.ic_hearts
import cards.card.ui.generated.resources.ic_spades
import com.martanhub.card.core.FrenchPlayingCard
import com.martanhub.card.core.FrenchRank
import com.martanhub.card.core.FrenchSuit
import com.martanhub.card.core.PlayingCard
import com.martanhub.card.core.Rank
import com.martanhub.card.core.Suit
import org.jetbrains.compose.resources.painterResource

@Composable
fun Card(
    modifier: Modifier = Modifier,
    playingCard: PlayingCard
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        RankPlacement.all.forEach { rankPlacement ->
            Column(
                modifier = Modifier
                    .align(rankPlacement.alignment)
                    .rotate(rankPlacement.rotation),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = playingCard.rank.string(),
                    style = MaterialTheme.typography.displayMedium,
                    color = playingCard.suit.color()
                )
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(playingCard.suit.drawable()),
                    tint = playingCard.suit.color(),
                    contentDescription = null
                )
            }
        }
    }
}

private fun Rank.string() = when (this) {
    is FrenchRank -> string()
    else -> throw IllegalArgumentException("Rank $this is unsupported")
}

private fun FrenchRank.string() = when (this) {
    FrenchRank.TWO -> "2"
    FrenchRank.THREE -> "3"
    FrenchRank.FOUR -> "4"
    FrenchRank.FIVE -> "5"
    FrenchRank.SIX -> "6"
    FrenchRank.SEVEN -> "7"
    FrenchRank.EIGHT -> "8"
    FrenchRank.NINE -> "9"
    FrenchRank.TEN -> "10"
    FrenchRank.JACK -> "J"
    FrenchRank.QUEEN -> "Q"
    FrenchRank.KING -> "K"
    FrenchRank.ACE -> "A"
}

private fun Suit.drawable() = when (this) {
    is FrenchSuit -> drawable()
    else -> throw IllegalArgumentException("Suit $this is unsupported")
}

private fun FrenchSuit.drawable() = when (this) {
    FrenchSuit.CLUBS -> Res.drawable.ic_clubs
    FrenchSuit.DIAMONDS -> Res.drawable.ic_diamonds
    FrenchSuit.HEARTS -> Res.drawable.ic_hearts
    FrenchSuit.SPADES -> Res.drawable.ic_spades
}

private fun Suit.color() = when (this) {
    is FrenchSuit -> color()
    else -> throw IllegalArgumentException("Suit $this is unsupported")
}

private fun FrenchSuit.color() = when (this) {
    FrenchSuit.CLUBS -> Color.Black
    FrenchSuit.DIAMONDS -> Color.Red
    FrenchSuit.HEARTS -> Color.Red
    FrenchSuit.SPADES -> Color.Black
}

private sealed interface RankPlacement {
    val alignment: Alignment
    val rotation: Float

    data object TopStart : RankPlacement {
        override val alignment: Alignment = Alignment.TopStart
        override val rotation: Float = 0f
    }

    data object TopEnd : RankPlacement {
        override val alignment: Alignment = Alignment.TopEnd
        override val rotation: Float = 0f
    }

    data object BottomStart : RankPlacement {
        override val alignment: Alignment = Alignment.BottomStart
        override val rotation: Float = 180f
    }

    data object BottomEnd : RankPlacement {
        override val alignment: Alignment = Alignment.BottomEnd
        override val rotation: Float = 180f
    }

    companion object {
        val all = listOf(TopStart, TopEnd, BottomStart, BottomEnd)
    }
}

@Preview
@Composable
fun CardPreview(
    @PreviewParameter(CardPreviewParameterProvider::class) card: PlayingCard
) {
    Card(playingCard = card)
}

private class CardPreviewParameterProvider : PreviewParameterProvider<PlayingCard> {
    override val values = sequenceOf(
        FrenchPlayingCard(
            rank = FrenchRank.TWO,
            suit = FrenchSuit.CLUBS
        ),
        FrenchPlayingCard(
            rank = FrenchRank.FIVE,
            suit = FrenchSuit.DIAMONDS
        ),
        FrenchPlayingCard(
            rank = FrenchRank.JACK,
            suit = FrenchSuit.HEARTS
        ),
        FrenchPlayingCard(
            rank = FrenchRank.ACE,
            suit = FrenchSuit.SPADES
        )
    )
}