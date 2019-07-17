package cbedoy.streamclient.post

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.streamclient.R
import kotlinx.android.synthetic.main.fragment_post.*
import org.jetbrains.anko.AnkoLogger

class PostView : Fragment(), AnkoLogger{

    lateinit var viewModel : PostViewModel
    var adapter = PostAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.post_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_explorer -> {
                findNavController().navigate(R.id.open_explorer_view)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadActivities()
    }
}