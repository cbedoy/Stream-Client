package cbedoy.streamclient.explorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cbedoy.streamclient.R
import cbedoy.streamclient.post.PostAdapter
import cbedoy.streamclient.post.PostViewModel
import cbedoy.streamclient.post.dialogs.ReactionsDialog
import cbedoy.streamclient.providers.ReactionsProvider
import io.getstream.core.models.EnrichedActivity
import kotlinx.android.synthetic.main.fragment_explorer.*

class ExplorerView : Fragment(){

    lateinit var viewModel: ExplorerViewModel
    lateinit var postViewModel: PostViewModel
    var adapter = PostAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        explorer_recycler_view.layoutManager = LinearLayoutManager(activity)
        explorer_recycler_view.adapter = adapter
        explorer_recycler_view.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(ExplorerViewModel::class.java)
        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)


        postViewModel.selectedActivity.observe(this, Observer {onSelected ->
            if (onSelected){
                findNavController().navigate(R.id.open_commenting_view)
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
        adapter.listener = object : PostAdapter.PostHolderListener {
            override fun onSelectedOptionsFromActivity(options: PostAdapter.PostHolderListener.OPTIONS, activity: EnrichedActivity) {
                if (options == PostAdapter.PostHolderListener.OPTIONS.LIKE){
                    val dialog = ReactionsDialog()
                    dialog.listener = object : ReactionsDialog.ReactionsDialogListener{
                        override fun onSelectedReactionType(type: ReactionsProvider.REACTION) {
                            postViewModel.handleReactionFromActivity(type.name, activity.id)
                        }
                    }

                    if (getActivity() is FragmentActivity){
                        dialog.show(getActivity()!!.supportFragmentManager, ReactionsDialog::class.java.toString())
                    }
                }else if (options == PostAdapter.PostHolderListener.OPTIONS.COMMENT){
                    postViewModel.openCommentingFromActivity(activity)
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadExplorer(arrayListOf("alex", "vanessa", "system", "carlos"))
    }
}