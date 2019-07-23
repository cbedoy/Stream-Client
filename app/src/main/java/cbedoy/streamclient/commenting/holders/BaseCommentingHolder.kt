package cbedoy.streamclient.commenting.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cbedoy.streamclient.models.Message
import kotlinx.android.extensions.LayoutContainer

abstract class BaseCommentingHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    abstract fun reload(message: Message)
}