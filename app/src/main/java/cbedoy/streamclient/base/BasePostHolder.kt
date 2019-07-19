package cbedoy.streamclient.base


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.R
import cbedoy.streamclient.post.PostAdapter
import cbedoy.streamclient.post.PostAdapter.PostHolderListener.OPTIONS.*
import io.getstream.core.models.Activity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_options.*

abstract class BasePostHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    var listener: PostAdapter.PostHolderListener? = null
    private lateinit var _activity: Activity

    open fun reload(activity: Activity){
        _activity = activity
    }

    fun linkReactions() {
        val listener = View.OnClickListener { v ->
            val option = when(v.id){
                R.id.post_option_comment -> COMMENT
                R.id.post_option_share-> SHARE
                R.id.post_option_like -> LIKE
                else -> NONE
            }
            listener?.onSelectedOptionsFromActivity(option, _activity)
        }

        post_option_comment.setOnClickListener(listener)
        post_option_like.setOnClickListener(listener)
        post_option_share.setOnClickListener(listener)
    }

}