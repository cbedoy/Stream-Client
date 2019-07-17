package cbedoy.streamclient

import cbedoy.streamclient.models.User
import io.getstream.client.Client
import java.security.MessageDigest

object UtilsProvider{

    private var user: User = User()
    private var client: Client =
        Client.builder("n6dqxby6gcfa",
            "uxs6q58j9gwyk9rybydwhx8qmemyadfjg5vh88x9pmtchmxw95f6bbbgxtcudm66").build()

    fun sha1() = user.nickname.md5()

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

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}