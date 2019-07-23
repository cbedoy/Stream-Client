package cbedoy.streamclient.commenting

import cbedoy.streamclient.models.Message
import cbedoy.streamclient.providers.UtilsProvider
import cbedoy.streamclient.util.StreamUtil
import io.getstream.core.models.Reaction
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object CommentingRepository {
    fun prepareActivity() : List<Any> {
        val result = ArrayList<Any>()
        val selectedActivity = UtilsProvider.getSelectedActivity()

        result.add(selectedActivity)

        val reactionCounts = selectedActivity.latestReactions
        if (reactionCounts.containsKey("message")){
            val comments = reactionCounts["message"]
            comments?.forEach {
                //val date = dateFromReaction(it) //TODO Get Fixed date

                val owner = isOwnerFromReaction(it)

                result.add(Message(it, owner, it.userID))
            }
        }
        return result
    }

    private fun isOwnerFromReaction(it: Reaction) : Boolean {
        val activityData = it.activityData
        val nickname = activityData["nickname"] as String
        return nickname == UtilsProvider.getNickname()
    }

    private fun dateFromReaction(it: Reaction): Date {
        val createdAt = it.extra["created_at"] as String

        val formatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        return formatter.parse(createdAt)
    }

    fun sendMessage(messageText: String): Message? {

        val nickname = UtilsProvider.getNickname()
        val selectedActivity = UtilsProvider.getSelectedActivity()

        val reaction = StreamUtil.addMessageToActivity(nickname, selectedActivity.id, messageText)

        if (reaction != null){
            return Message(reaction, true, nickname)
        }
        return null
    }

}