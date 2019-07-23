package cbedoy.streamclient.commenting.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.models.Message
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.commenting_in_plain_holder.*
import kotlinx.android.synthetic.main.commenting_out_plain_holder.*
import kotlinx.android.synthetic.main.commenting_out_plain_holder.message_container

class CommentingOutHolder(override val containerView: View) : BaseCommentingHolder(containerView),
    LayoutContainer {

    override fun reload(message: Message) {
        val extra = message.reaction.extra

        val text = extra["text"]

        message_container.text = "$text"
    }

}