package cbedoy.streamclient

import io.getstream.client.Client
import io.getstream.client.FlatFeed
import io.getstream.core.http.Token
import io.getstream.core.models.Activity
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

    fun addOrRemoveReaction(nickname: String,  kind: String, activity: Activity): Reaction? {
        val reaction = Reaction.Builder()
            .kind(kind)
            .activityID(activity.id)
            .build()

        return client.reactions().add(nickname, reaction).get()
    }
}