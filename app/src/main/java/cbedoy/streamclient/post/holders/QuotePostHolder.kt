package cbedoy.streamclient.post.holders

import android.view.View
import cbedoy.streamclient.base.BasePostHolder
import io.getstream.core.models.Activity
import kotlinx.android.synthetic.main.post_anonymous.*

class QuotePostHolder (override val containerView: View) : BasePostHolder(containerView){
    override fun reload(activity: Activity) {
        super.reload(activity)

        val extra = activity.extra

        quote_text.text = "${extra["content"]}"

        //Keep in mind you should have a post options view injection on your layout :)
        //post_options.xml
        linkReactions()
    }
}