package cbedoy.streamclient.post.holders

import android.view.View
import cbedoy.streamclient.base.BaseHolder
import io.getstream.core.models.Activity
import kotlinx.android.synthetic.main.post_anonymous.*

class QuotePostHolder (override val containerView: View) : BaseHolder(containerView){
    override fun reload(any: Any) {
        any as Activity

        val extra = any.extra

        quote_text.text = "${extra["content"]}"
    }

}