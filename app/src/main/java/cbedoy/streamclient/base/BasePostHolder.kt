package cbedoy.streamclient.base


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.R
import cbedoy.streamclient.post.PostAdapter
import cbedoy.streamclient.post.PostAdapter.PostHolderListener.OPTIONS.*
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_options.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

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

        val size = _enrichedActivity.reactionCounts
        val latestReactions = _enrichedActivity.latestReactions
        val ownReactions = _enrichedActivity.ownReactions

        info(size)
        info(latestReactions)
        info(ownReactions)

    }

}