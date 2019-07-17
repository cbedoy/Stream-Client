package cbedoy.streamclient

import io.getstream.client.Client
import io.getstream.client.FlatFeed
import io.getstream.core.http.Token

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
}