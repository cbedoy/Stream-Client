package cbedoy.streamclient.base


import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.R
import cbedoy.streamclient.providers.ReactionsProvider
import cbedoy.streamclient.post.PostAdapter
import cbedoy.streamclient.post.PostAdapter.PostHolderListener.OPTIONS.*
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_options.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.textColor

abstract class BasePostHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer, AnkoLogger {

    var listener: PostAdapter.PostHolderListener? = null
    private lateinit var _enrichedActivity: EnrichedActivity

    open fun reload(enrichedActivity: EnrichedActivity){
        _enrichedActivity = enrichedActivity
    }

    fun linkReactions() {
        val listener = View.OnClickListener { v ->
            val option = when(v.id){
                R.id.post_option_comment -> COMMENT
                R.id.post_option_share-> SHARE
                R.id.post_option_like -> LIKE
                else -> NONE
            }
            listener?.onSelectedOptionsFromActivity(option, _enrichedActivity)
        }

        post_option_comment.setOnClickListener(listener)
        post_option_like.setOnClickListener(listener)
        post_option_share.setOnClickListener(listener)

        val reactionCounts = _enrichedActivity.reactionCounts
        val latestReactions = _enrichedActivity.latestReactions
        val ownReactions = _enrichedActivity.ownReactions

        if (reactionCounts.isNotEmpty()){
            var reactionText = ""
            var reactionColor = ""
            reactionCounts.forEach { (key, value) ->
                val data = ReactionsProvider.getReactionData(key)

                reactionText+= "${data.avatar} ${value.toInt()} "

                reactionColor = data.color
            }

            post_option_like.text = reactionText
            post_option_like.textColor = Color.parseColor(reactionColor)
        }else{
            post_option_like.text = "LIKE"
            post_option_like.textColor = Color.parseColor("#333333")
        }


        info(latestReactions)
        info(ownReactions)

    }

}