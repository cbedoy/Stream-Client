package cbedoy.streamclient.post

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.streamclient.R
import kotlinx.android.synthetic.main.fragment_post.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

class PostView : Fragment(), AnkoLogger{

    lateinit var viewModel : PostViewModel
    var adapter = PostAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        viewModel.result.observe(this, Observer {
            if (!adapter.dataModel.contains(it)){
                adapter.dataModel.add(0, it)

                adapter.notifyItemInserted(0)
            }
        })
        viewModel.activities.observe(this, Observer { activities ->
            if (adapter.dataModel.isEmpty()){
                adapter.dataModel.addAll(activities)

                adapter.notifyDataSetChanged()
            }else{
                activities.forEach {
                    if (!adapter.dataModel.contains(it)){
                        adapter.dataModel.add(it)

                        adapter.notifyItemChanged(adapter.dataModel.indexOf(it))
                    }
                }
            }
        })

        post_view_recycler_view.layoutManager = LinearLayoutManager(activity)
        post_view_recycler_view.adapter = adapter
        post_view_recycler_view.setHasFixedSize(true)

        post_view_send.setOnClickListener {
            val content = post_view_field.text

            if (content.length > 8) {
                viewModel.sendTweet(post_view_field.text.toString())
                post_view_field.text = null
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadActivities()
    }
}