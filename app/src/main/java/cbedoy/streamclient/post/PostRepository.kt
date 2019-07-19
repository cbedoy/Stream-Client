package cbedoy.streamclient.post

import cbedoy.streamclient.ContentProvider.randomAvatar
import cbedoy.streamclient.ContentProvider.randomContent
import cbedoy.streamclient.ContentProvider.randomMood
import cbedoy.streamclient.ContentProvider.randomName
import cbedoy.streamclient.ContentProvider.randomNumber
import cbedoy.streamclient.ContentProvider.randomQuote
import cbedoy.streamclient.StreamUtil
import cbedoy.streamclient.UtilsProvider
import cbedoy.streamclient.post.dialogs.ReactionsDialog.ReactionsDialogListener.REACTION
import io.getstream.core.models.Activity
import io.getstream.core.models.EnrichedActivity
import io.getstream.core.models.Reaction
import io.getstream.core.options.EnrichmentFlags
import io.getstream.core.options.Limit
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import kotlin.random.Random


object PostRepository : AnkoLogger {
    //  Actor is the user id of the person performing the activity.
    //  Tweet is a custom field containing the message.
    //  Verb is the type of activity the actor is engaging in.
    //  Object is the id of the tweet object in your database.

    fun addActivity(tweet: String) : Activity {
        val nickname = UtilsProvider.getNickname()
        val feed = StreamUtil.flatFeed("user", nickname)

        val activity: Activity
        if (nickname == "system"){
            val criteria = Random(System.currentTimeMillis()).nextInt(9) % 2 == 0
            activity = if (criteria){
                createTinderContent()
            }else{
                createQuoteContent()
            }
        }else{
            activity = Activity.builder()
                .actor(nickname)  //
                .verb("tweet")
                .`object`(UUID.randomUUID().toString())
                .extraField("tweet", tweet)
                .extraField("content", randomContent())
                .extraField("mood", randomMood())
                .extraField("number", randomNumber())
                .build()
        }

        return feed.addActivity(activity).get()
    }

    private fun createTinderContent() : Activity{
        return Activity.builder()
            .actor("system")
            .verb("tweet")
            .`object`(UUID.randomUUID().toString())
            .extraField("type", "tinder")
            .extraField("name", randomName())
            .extraField("avatar", randomAvatar())
            .build()
    }



    private fun createQuoteContent() : Activity{
        return Activity.builder()
            .actor("system")
            .verb("tweet")
            .`object`(UUID.randomUUID().toString())
            .extraField("type", "quote")
            .extraField("content", randomQuote())
            .build()
    }

    fun loadActivities(): MutableList<Activity>? {
        val userId = UtilsProvider.getNickname()
        val feed = StreamUtil.flatFeed("user", userId)

        val enrichedActivities = loadEnrichedActivities()
        info(enrichedActivities)


        return feed.getActivities(Limit(25)).get()
    }

    fun loadEnrichedActivities(): MutableList<EnrichedActivity>? {
        val userId = UtilsProvider.getNickname()
        val feed = StreamUtil.flatFeed("user", userId)

        val list = feed.getEnrichedActivities(
            Limit(25),
            EnrichmentFlags()
                .withRecentReactions()
                .withReactionCounts()
        ).get()

        info(list)

        return list
    }

    fun addReactionToActivity(reactionType: String, activityId: String): Reaction? {
        val nickname = UtilsProvider.getNickname()

        if (reactionType != REACTION.NONE.name){
            return StreamUtil.addOrRemoveReactionFromActivity(nickname, reactionType, activityId)
        }
        return null
    }
}