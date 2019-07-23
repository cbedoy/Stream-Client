package cbedoy.streamclient.post.holders

import android.view.View
import cbedoy.streamclient.base.BasePostHolder
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.synthetic.main.post_holder.*
import kotlinx.android.synthetic.main.post_options.*
import java.text.DateFormat

open class PostHolder (override val containerView: View) : BasePostHolder(containerView){
    override fun reload(enrichedActivity: EnrichedActivity) {
        super.reload(enrichedActivity)

        val extra = enrichedActivity.extra
        val format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(enrichedActivity.time)

        tweet_nickname.text = enrichedActivity.actor.id
        tweet_content.text = "${extra["tweet"]}"
        tweet_date.text = format

        if (showOptions) linkReactions() else post_option_container.visibility = View.GONE
    }
}