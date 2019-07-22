package cbedoy.streamclient.providers

import cbedoy.streamclient.models.User
import io.getstream.core.models.EnrichedActivity
import java.security.MessageDigest

object UtilsProvider{

    private var user: User = User()
    private lateinit var selectedActivity: EnrichedActivity

    fun setToken(token: String) {
        user.token = token
    }

    fun setNickname(nickname: String){
        user.nickname = nickname
    }

    fun getNickname() = user.nickname

    fun getSelectedActivity() = selectedActivity

    fun setSelectedActivity(activity: EnrichedActivity){
        selectedActivity = activity
    }
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}