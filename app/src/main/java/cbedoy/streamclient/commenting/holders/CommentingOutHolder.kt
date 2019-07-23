package cbedoy.streamclient.commenting.holders

import android.view.View
import cbedoy.streamclient.models.Message
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.commenting_out_plain_holder.*
import java.text.DateFormat

class CommentingOutHolder(override val containerView: View) : BaseCommentingHolder(containerView),
    LayoutContainer {

    override fun reload(message: Message) {
        val activityData = message.reaction.activityData

        val text = activityData["text"]
        val createdAt = message.createAt
        val format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(createdAt)

        message_text.text = "$text"
        message_time_ago.text = format
    }

}