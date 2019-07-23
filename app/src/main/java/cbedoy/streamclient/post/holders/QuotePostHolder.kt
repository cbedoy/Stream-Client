package cbedoy.streamclient.post.holders

import android.view.View
import cbedoy.streamclient.base.BasePostHolder
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.synthetic.main.post_anonymous.*
import kotlinx.android.synthetic.main.post_options.*

class QuotePostHolder (override val containerView: View) : BasePostHolder(containerView){
    override fun reload(enrichedActivity: EnrichedActivity) {
        super.reload(enrichedActivity)

        val extra = enrichedActivity.extra

        quote_text.text = "${extra["content"]}"

        if (showOptions) linkReactions() else post_option_container.visibility = View.GONE
    }
}