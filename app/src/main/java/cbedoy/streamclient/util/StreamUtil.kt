package cbedoy.streamclient.util

import cbedoy.streamclient.providers.UtilsProvider
import io.getstream.client.Client
import io.getstream.client.FlatFeed
import io.getstream.core.LookupKind
import io.getstream.core.http.Token
import io.getstream.core.models.Reaction
import io.getstream.core.options.Filter
import java8.util.concurrent.CompletableFuture


object StreamUtil {

    val secret = "uxs6q58j9gwyk9rybydwhx8qmemyadfjg5vh88x9pmtchmxw95f6bbbgxtcudm66"
    val apiKey = "n6dqxby6gcfa"
    val developJWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyZXNvdXJjZSI6IioiLCJhY3Rpb24iOiIqIiwiZmVlZF9pZCI6IioifQ.p31LovjFsSEfSrAv-Np0diPENZ3c21Rfr3nxBFn_ukA"

    private var client: Client =
        Client.builder(apiKey, secret).build()

    fun flatFeed(type: String, user: String) : FlatFeed {
        return client.flatFeed(type, user)
    }

    fun reactionsFromActivity(activityId: String, type: String): MutableList<Reaction>? {
        return client.reactions().filter(LookupKind.ACTIVITY, activityId, type).get()
    }

    fun frontendToken(nickname: String) : Token?{
        return client.frontendToken(nickname)
    }

    fun addOrRemoveReactionFromActivity(nickname: String, kind: String, activityId: String): Reaction? {
        val reaction = Reaction.Builder()
            .kind(kind)
            .activityID(activityId)
            .build()

        return client.reactions().add(nickname, reaction).get()
    }

    fun addMessageToActivity(nickname: String, activityId: String, messageText: String) : Reaction?{

        val avatar = UtilsProvider.avatarFromNickname(nickname)

        val reaction = Reaction.Builder()
            .kind("message")
            .activityID(activityId)
            .extraField("avatar", avatar)
            .extraField("text", messageText)
            .extraField("nickname", nickname)
            .build()

        return client.reactions().add(nickname, reaction).get()
    }
}