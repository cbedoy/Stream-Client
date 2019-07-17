package cbedoy.streamclient.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    abstract fun reload(any: Any)
}