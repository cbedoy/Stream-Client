package cbedoy.streamclient.post.holders

import android.view.View
import cbedoy.streamclient.base.BasePostHolder
import io.getstream.core.models.Activity
import kotlinx.android.synthetic.main.post_holder.tweet_content
import kotlinx.android.synthetic.main.post_holder.tweet_date
import kotlinx.android.synthetic.main.post_holder.tweet_nickname
import java.text.DateFormat

open class PostHolder (override val containerView: View) : BasePostHolder(containerView){
    override fun reload(activity: Activity) {
        super.reload(activity)

        val extra = activity.extra
        val format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(activity.time)

        tweet_nickname.text = activity.actor
        tweet_content.text = "${extra["tweet"]}"
        tweet_date.text = format
    }
}