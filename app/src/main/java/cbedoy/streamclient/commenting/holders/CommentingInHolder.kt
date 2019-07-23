package cbedoy.streamclient.commenting.holders

import android.view.View
import cbedoy.streamclient.models.Message
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.commenting_in_plain_holder.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale.US

class CommentingInHolder (override val containerView: View) : BaseCommentingHolder(containerView),
    LayoutContainer {

    override fun reload(message: Message) {
        val activityData = message.reaction.activityData

        val text = activityData["text"]
        val avatar = activityData["avatar"]
        val createdAt = message.createAt
        val format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(createdAt)

        message_text.text = "$text"
        message_time_ago.text = format

        Glide.with(message_avatar)
            .load(avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(message_avatar)

    }

}