package cbedoy.streamclient.explorer

import cbedoy.streamclient.util.StreamUtil
import cbedoy.streamclient.providers.UtilsProvider
import io.getstream.core.models.EnrichedActivity
import io.getstream.core.options.EnrichmentFlags
import io.getstream.core.options.Limit
import org.jetbrains.anko.AnkoLogger

object ExplorerRepository : AnkoLogger {
    fun loadExplorer(users: List<String>) : MutableList<EnrichedActivity>? {
        val timeline = StreamUtil.flatFeed("timeline", UtilsProvider.getNickname())

        users.forEach { userName ->
            val feed = StreamUtil.flatFeed("user", userName)

            timeline.follow(feed).get()
        }
        return timeline.getEnrichedActivities(
            Limit(25),
            EnrichmentFlags()
            .withRecentReactions()
            .withReactionCounts()).get()
    }
}