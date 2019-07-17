package cbedoy.streamclient.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.R
import cbedoy.streamclient.base.BaseHolder
import io.getstream.core.models.Activity

class PostAdapter : RecyclerView.Adapter<BaseHolder>() {

    var dataModel: ArrayList<Activity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_holder, parent, false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.reload(dataModel[position])
    }
}