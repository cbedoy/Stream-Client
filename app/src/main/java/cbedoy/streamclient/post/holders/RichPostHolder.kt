package cbedoy.streamclient.post.holders

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.synthetic.main.post_options.*
import kotlinx.android.synthetic.main.rich_post_holder.*

class RichPostHolder(override val containerView: View) : PostHolder(containerView){
    @SuppressLint("SetTextI18n")
    override fun reload(enrichedActivity: EnrichedActivity) {
        super.reload(enrichedActivity)

        val extra = enrichedActivity.extra
        val content = extra["content"] as String

        tweet_mood.text = "${extra["number"]}% of ${extra["mood"]}"

        Glide.with(tweet_image)
            .load(content)
            .into(tweet_image)

        if (showOptions) linkReactions() else post_option_container.visibility = View.GONE
    }
}