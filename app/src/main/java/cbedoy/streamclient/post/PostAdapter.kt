package cbedoy.streamclient.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.R
import cbedoy.streamclient.base.BaseHolder
import cbedoy.streamclient.post.holders.PostHolder
import cbedoy.streamclient.post.holders.QuotePostHolder
import cbedoy.streamclient.post.holders.RichPostHolder
import cbedoy.streamclient.post.holders.TinderPostHolder
import io.getstream.core.models.Activity

class PostAdapter : RecyclerView.Adapter<BaseHolder>() {

    var dataModel: ArrayList<Activity> = ArrayList()
    private val normal = 0
    private val rich = 1
    private val tinder = 2
    private val quote = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when (viewType) {
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
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.post_anonymous, parent, false)
                QuotePostHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun getItemViewType(position: Int): Int {
        val activity = dataModel[position]
        val extra = activity.extra

        val type = extra["type"]

        return when {
            type == "tinder" -> tinder
            type == "quote" -> quote
            extra.size == 1 -> normal
            else -> rich
        }


    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.reload(dataModel[position])
    }
}