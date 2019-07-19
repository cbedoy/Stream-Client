package cbedoy.streamclient.post.holders

import android.view.View
import cbedoy.streamclient.base.BasePostHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.synthetic.main.post_tinder.*

class TinderPostHolder(override val containerView: View) : BasePostHolder(containerView){
    override fun reload(enrichedActivity: EnrichedActivity) {
        super.reload(enrichedActivity)
        val extra = enrichedActivity.extra

        post_tinder_nickname.text = "${extra["name"]}"

        Glide.with(post_tinder_avatar)
            .load(extra["avatar"] as String)
            .apply(RequestOptions.circleCropTransform())
            .into(post_tinder_avatar)
    }

}