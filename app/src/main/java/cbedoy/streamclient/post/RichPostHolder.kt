package cbedoy.streamclient.post

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import io.getstream.core.models.Activity
import kotlinx.android.synthetic.main.rich_post_holder.*

class RichPostHolder(override val containerView: View) : PostHolder(containerView){
    @SuppressLint("SetTextI18n")
    override fun reload(any: Any) {
        super.reload(any)
        any as Activity

        val extra = any.extra
        val content = extra["content"] as String

        tweet_mood.text = "${extra["number"]}% of ${extra["mood"]}"

        Glide.with(tweet_image)
            .load(content)
            .into(tweet_image)
    }
}