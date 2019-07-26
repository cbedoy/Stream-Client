package cbedoy.streamclient.commenting

import cbedoy.streamclient.models.Message
import cbedoy.streamclient.providers.UtilsProvider
import cbedoy.streamclient.retrofit.RetrofitService
import cbedoy.streamclient.services.ReactionService
import cbedoy.streamclient.util.StreamUtil
import cbedoy.streamclient.util.StreamUtil.secret
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.getstream.client.ReactionsClient
import io.getstream.core.models.Reaction
import io.getstream.core.options.EnrichmentFlags
import io.getstream.core.options.Limit
import io.getstream.core.utils.Auth
import io.getstream.core.utils.Auth.buildReactionsToken
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.onComplete
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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

    fun requestReactions(type: String): ArrayList<Message> {
        val result = ArrayList<Message>()
        val selectedActivity = UtilsProvider.getSelectedActivity().id
        val reactionsFromActivity = StreamUtil.reactionsFromActivity(selectedActivity, type)

        reactionsFromActivity?.forEach {
            val owner = isOwnerFromReaction(it)

            result.add(Message(it, owner, it.userID))
        }
        return result
    }

}