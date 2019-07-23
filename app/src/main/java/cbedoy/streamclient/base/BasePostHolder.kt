package cbedoy.streamclient.base


import android.annotation.SuppressLint
import android.content.res.Resources
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
    var showOptions: Boolean = true
    private lateinit var _enrichedActivity: EnrichedActivity

    open fun reload(enrichedActivity: EnrichedActivity){
        _enrichedActivity = enrichedActivity
    }

    @SuppressLint("SetTextI18n")
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

        if (hasSupportedReactions()){
            var reactionText = ""
            var reactionColor = ""
            reactionCounts.forEach { (key, value) ->
                val data = ReactionsProvider.getReactionData(key)

                if (data != null) {
                    reactionText += "${data.avatar} ${value.toInt()} "

                    reactionColor = data.color
                }
            }

            post_option_like.text = reactionText
            post_option_like.textColor = Color.parseColor(reactionColor)
        }else{
            post_option_like.text = "LIKE"
            post_option_like.textColor = Color.parseColor("#333333")
        }

        if (hasMessages()){
            post_option_comment.text = "${numberMessages()} comments"
        }else{
            post_option_comment.text = "COMMENT"
        }
    }

    private fun hasMessages() : Boolean{
        return _enrichedActivity.reactionCounts.containsKey("message")
    }

    private fun numberMessages() : Int {
        return _enrichedActivity.reactionCounts["message"]!!.toInt()
    }

    private fun hasSupportedReactions() : Boolean{
        val keys = _enrichedActivity.reactionCounts.keys
        val allowedReactions = ReactionsProvider.allowedReactions()
        keys.forEach {
            if (allowedReactions.contains(it))
                return true
        }
        return false
    }

}