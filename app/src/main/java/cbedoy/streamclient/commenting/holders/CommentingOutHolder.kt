package cbedoy.streamclient.commenting.holders

import android.view.View
import cbedoy.streamclient.models.Message
import cbedoy.streamclient.util.TimeAgoUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.commenting_out_plain_holder.*

class CommentingOutHolder(override val containerView: View) : BaseCommentingHolder(containerView),
    LayoutContainer {

    override fun reload(message: Message) {
        val activityData = message.reaction.activityData

        val text = activityData["text"]
        val format = TimeAgoUtil.getTimeAgo(message.timestamp)

        message_text.text = "$text"
        message_time_ago.text = format
    }

}