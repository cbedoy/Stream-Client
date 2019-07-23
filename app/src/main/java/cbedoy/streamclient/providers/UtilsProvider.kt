package cbedoy.streamclient.providers

import cbedoy.streamclient.models.User
import io.getstream.core.models.EnrichedActivity
import java.security.MessageDigest

object UtilsProvider{

    private var user: User = User()
    private lateinit var selectedActivity: EnrichedActivity
    private var subscribers = arrayListOf("alex", "vanessa", "system", "carlos")
    private var avatars = arrayListOf(
        "https://avatars1.githubusercontent.com/u/6551029?s=460&v=4",
        "https://avatars1.githubusercontent.com/u/3089882?s=460&v=4",
        "https://avatars1.githubusercontent.com/u/89638?s=460&v=4",
        "https://avatars0.githubusercontent.com/u/5570799?s=460&v=4"
    )

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

    fun subscribers(): List<String> {
        return subscribers
    }

    fun avatarFromNickname(nickname: String): String {
        val indexOf = subscribers.indexOf(nickname)
        if (indexOf != -1)
            return avatars[indexOf]
        return "https://avatars2.githubusercontent.com/u/3019167?s=460&v=4"
    }
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}