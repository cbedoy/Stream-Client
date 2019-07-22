package cbedoy.streamclient.providers

import cbedoy.streamclient.providers.ReactionsProvider.REACTION.*

object ReactionsProvider {
    data class ReactionData(val name: String, val avatar: String, val color: String = "#FFDE03")

    private val reactions = hashMapOf(
        ANGRY.name to ReactionData(
            ANGRY.name,
            "\uD83D\uDE20",
            "#B00020"
        ),          //😠
        ENJOY.name to ReactionData(
            ENJOY.name,
            "\uD83D\uDE06",
            "#FFDE03"
        ),          //😆
        LIKE.name to ReactionData(LIKE.name, "\uD83D\uDC4D", "#0336FF"),            //👍
        LOVE.name to ReactionData(LOVE.name, "\uD83D\uDC96", "#FF0266"),            //💖
        SAD.name to ReactionData(SAD.name, "\uD83D\uDE22", "#FF9800"),              //😢
        SURPRISED.name to ReactionData(
            SURPRISED.name,
            "\uD83D\uDE31",
            "#00BCD4"
        )   //😱
    )

    fun getReactionData(reaction: String) : ReactionData {
        return reactions[reaction]!!
    }

    enum class REACTION {
        ANGRY, ENJOY, LIKE, LOVE, SAD, SURPRISED, NONE
    }
}