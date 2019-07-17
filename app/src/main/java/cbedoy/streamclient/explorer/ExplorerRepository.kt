package cbedoy.streamclient.explorer

import cbedoy.streamclient.StreamUtil
import cbedoy.streamclient.UtilsProvider
import io.getstream.core.models.Activity
import io.getstream.core.options.Pagination
import org.jetbrains.anko.AnkoLogger

object ExplorerRepository : AnkoLogger {
    fun loadExplorer(users: List<String>) : List<Activity> {
        val timeline = StreamUtil.flatFeed("timeline", UtilsProvider.getNickname())

        users.forEach { userName ->
            val feed = StreamUtil.flatFeed("user", userName)

            timeline.follow(feed).get()
        }
        return timeline.getActivities(Pagination().limit(20)).get()
    }
}