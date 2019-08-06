package cbedoy.streamclient.util

import com.github.marlonlom.utilities.timeago.TimeAgo

object TimeAgoUtil {
    fun getTimeAgo(_time: Long): String? {
        return TimeAgo.using(_time)
    }
}