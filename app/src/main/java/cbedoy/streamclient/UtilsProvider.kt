package cbedoy.streamclient

import cbedoy.streamclient.models.User
import io.getstream.client.Client

object UtilsProvider{

    private var user: User = User()
    private var client: Client =
        Client.builder("n6dqxby6gcfa",
            "uxs6q58j9gwyk9rybydwhx8qmemyadfjg5vh88x9pmtchmxw95f6bbbgxtcudm66").build()


    fun getClient() = client

    fun setToken(token: String) {
        user.token = token
    }

    fun setNickname(nickname: String){
        user.nickname = nickname
    }

    fun getToken() = user.token

    fun getNickname() = user.nickname
}