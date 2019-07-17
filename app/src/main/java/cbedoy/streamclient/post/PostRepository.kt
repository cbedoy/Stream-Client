package cbedoy.streamclient.post

import cbedoy.streamclient.UtilsProvider
import io.getstream.core.models.Activity
import io.getstream.core.options.Pagination
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

object PostRepository : AnkoLogger {
    //  Actor is the user id of the person performing the activity.
    //  Tweet is a custom field containing the message.
    //  Verb is the type of activity the actor is engaging in.
    //  Object is the id of the tweet object in your database.

    fun addActivity(tweet: String) : Activity {
        val userId = UtilsProvider.getNickname()
        val feed = UtilsProvider.getClient().flatFeed("user", userId)
        info(feed)
        val activity = Activity.builder()
            .actor(userId)  //
            .verb("tweet")
            .`object`(UUID.randomUUID().toString())
            .extraField("tweet", tweet)
            .build()

        info(activity)

        return feed.addActivity(activity).get()
    }

    fun loadActivities(): MutableList<Activity>? {
        val userId = UtilsProvider.getNickname()
        val feed = UtilsProvider.getClient().flatFeed("user", userId)
        return feed.getActivities(Pagination().limit(10)).get()
    }
}