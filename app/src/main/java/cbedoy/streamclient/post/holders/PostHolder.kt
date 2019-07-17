package cbedoy.streamclient.post.holders

import android.view.View
import cbedoy.streamclient.base.BaseHolder
import io.getstream.core.models.Activity
import kotlinx.android.synthetic.main.post_holder.*
import kotlinx.android.synthetic.main.post_holder.tweet_content
import kotlinx.android.synthetic.main.post_holder.tweet_date
import kotlinx.android.synthetic.main.post_holder.tweet_nickname
import kotlinx.android.synthetic.main.rich_post_holder.*
import java.text.DateFormat

open class PostHolder (override val containerView: View) : BaseHolder(containerView){
    override fun reload(any: Any) {
        any as Activity

        val extra = any.extra
        val format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(any.time)

        tweet_nickname.text = any.actor
        tweet_content.text = "${extra["tweet"]}"
        tweet_date.text = format
    }
}