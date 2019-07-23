package cbedoy.streamclient.commenting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.R
import cbedoy.streamclient.base.BasePostHolder
import cbedoy.streamclient.commenting.holders.BaseCommentingHolder
import cbedoy.streamclient.commenting.holders.CommentingInHolder
import cbedoy.streamclient.commenting.holders.CommentingOutHolder
import cbedoy.streamclient.models.Message
import cbedoy.streamclient.post.PostAdapter
import cbedoy.streamclient.post.holders.PostHolder
import cbedoy.streamclient.post.holders.QuotePostHolder
import cbedoy.streamclient.post.holders.RichPostHolder
import cbedoy.streamclient.post.holders.TinderPostHolder
import io.getstream.core.models.EnrichedActivity

class CommentingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataModel: ArrayList<Any> = ArrayList()
    var listener: PostAdapter.PostHolderListener? = null
    private val normal = 0
    private val rich = 1
    private val tinder = 2
    private val quote = 3
    private val messageIn = 4
    private val messageOut = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder : RecyclerView.ViewHolder =  when (viewType) {
            normal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.post_holder, parent, false)
                PostHolder(view)
            }
            rich -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rich_post_holder, parent, false)
                RichPostHolder(view)
            }
            tinder -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.post_tinder, parent, false)
                TinderPostHolder(view)
            }
            messageIn -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.commenting_in_plain_holder, parent, false)
                CommentingInHolder(view)
            }
            messageOut -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.commenting_out_plain_holder, parent, false)
                CommentingOutHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.post_anonymous, parent, false)
                QuotePostHolder(view)
            }
        }

        if (holder is BasePostHolder)
            holder.listener = listener

        return holder
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun getItemViewType(position: Int): Int {

        if (dataModel[position] is EnrichedActivity){
            val activity = dataModel[position] as EnrichedActivity
            val extra = activity.extra

            val type = extra["type"]

            return when {
                type == "tinder" -> tinder
                type == "quote" -> quote
                extra.containsKey("content") -> rich
                else -> normal
            }
        }else{
            val message = dataModel[position] as Message
            return if (message.owner) messageOut else messageIn
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BasePostHolder)
            holder.reload(dataModel[position] as EnrichedActivity)

        if (holder is BaseCommentingHolder)
            holder.reload(dataModel[position] as Message)
    }

}