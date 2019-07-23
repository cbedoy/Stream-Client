package cbedoy.streamclient.util

import cbedoy.streamclient.providers.UtilsProvider
import io.getstream.client.Client
import io.getstream.client.FlatFeed
import io.getstream.core.http.Token
import io.getstream.core.models.Reaction


object StreamUtil {
    private var client: Client =
        Client.builder("n6dqxby6gcfa",
            "uxs6q58j9gwyk9rybydwhx8qmemyadfjg5vh88x9pmtchmxw95f6bbbgxtcudm66").build()

    fun flatFeed(type: String, user: String) : FlatFeed {
        return client.flatFeed(type, user)
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