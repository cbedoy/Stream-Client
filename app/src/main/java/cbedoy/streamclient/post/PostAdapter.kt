package cbedoy.streamclient.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.R
import cbedoy.streamclient.base.BaseHolder
import io.getstream.core.models.Activity

class PostAdapter : RecyclerView.Adapter<BaseHolder>() {

    var dataModel: ArrayList<Activity> = ArrayList()
    private val normal = 0
    private val rich = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return if (viewType == normal){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.post_holder, parent, false)
            PostHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rich_post_holder, parent, false)
            RichPostHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun getItemViewType(position: Int): Int {
        val activity = dataModel[position]
        return if (activity.extra.size == 1){
            normal
        }else{
            rich
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.reload(dataModel[position])
    }
}