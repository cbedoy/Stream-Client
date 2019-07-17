package cbedoy.streamclient

import cbedoy.streamclient.models.User
import java.security.MessageDigest

object UtilsProvider{

    private var user: User = User()

    fun setToken(token: String) {
        user.token = token
    }

    fun setNickname(nickname: String){
        user.nickname = nickname
    }

    fun getNickname() = user.nickname
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}